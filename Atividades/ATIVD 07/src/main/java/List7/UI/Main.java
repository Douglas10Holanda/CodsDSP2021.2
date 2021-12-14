package List7.UI;

import java.util.List;

import javax.swing.JOptionPane;

import List7.DAO.FuncionarioDAO;
import List7.ENTITY.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("List7")
@EntityScan("List7.entity")
@EnableJpaRepositories("List7.DAO")
public class Main implements CommandLineRunner {
	@Autowired
	private FuncionarioDAO baseFuncionarios;

	public static void main(String[] args) {
		//SpringApplication.run(Principal.class, args);
		SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
		builder.headless(false).run(args);
	}
	
	public static void obterFuncionario(Funcionario func) {
		String cpf = JOptionPane.showInputDialog("CPF", func.getCpf());
		String matricula = JOptionPane.showInputDialog("Matricula", func.getMatricula());
		String nome = JOptionPane.showInputDialog("Nome", func.getNome());
		String email = JOptionPane.showInputDialog("Email", func.getEmail());
		String telefone = JOptionPane.showInputDialog("Telefone", func.getTelefone());

		func.setCpf(cpf);
		func.setMatricula(matricula);
		func.setNome(nome);
		func.setEmail(email);
		func.setTelefone(telefone);
	}

	public static void listaFuncionarios(List<Funcionario> funcionarios) {
		StringBuilder listagem = new StringBuilder();
		for(Funcionario func : funcionarios) {
			listagem.append(func).append("\n\n");
		}
		JOptionPane.showMessageDialog(null, listagem.length() == 0 ? "Nenhum funcionario encontrado" : listagem);
	}

	public static void listaFuncionario(Funcionario func) {
		JOptionPane.showMessageDialog(null, func == null ? "Nenhum funcionario encontrado" : func);
	}

	@Override
	public void run(String... args) throws Exception {
		String menu = "Olá, Seja bem-vindo :)\n\n" +
				"Escolha uma opção:\n" +
				"1 - Adicionar novo funcionario\n" +
				"2 - Atualizar funcionario por CPF\n" +
				"3 - Remover funcionario por CPF\n" +
				"4 - Exibir funcionario por CPF\n" +
				"5 - Exibir funcionario por id\n" +
				"6 - Exibir todos os funcionarios\n" +
				"7 - Exibir todos os funcionario que contém determinado nome\n" +
				"8 - Sair do menu";
		char opcao;
		do {
			Funcionario func;
			String cpf;
			opcao = JOptionPane.showInputDialog(menu).charAt(0);
			switch (opcao) {
				case '1':     // Inserir
					func = new Funcionario();
					obterFuncionario(func);
					baseFuncionarios.save(func);
					break;
				case '2':     // Atualizar por CPF
					cpf = JOptionPane.showInputDialog("Digite o CPF do funcionario que deseja alterar");
					func = baseFuncionarios.findFirstByCpf(cpf);
					obterFuncionario(func);
					baseFuncionarios.save(func);
					break;
				case '3':     // Remover por CPF
					cpf = JOptionPane.showInputDialog("CPF");
					func = baseFuncionarios.findFirstByCpf(cpf);
					if (func != null) {
						baseFuncionarios.deleteById(func.getId());
					} else {
						JOptionPane.showMessageDialog(null, "Não foi possível remover, pois o funcionario não foi encontrado.");
					}
					break;
				case '4':     // Exibir por CPF
					cpf = JOptionPane.showInputDialog("CPF");
					func = baseFuncionarios.buscaPorCpfViaConsultaNomeada(cpf);
					listaFuncionario(func);
					break;
				case '5':     // Exibir por id
					int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
					func = baseFuncionarios.findById(id).orElse(null);
					listaFuncionario(func);
					break;
				case '6':     // Exibir todos
					listaFuncionarios(baseFuncionarios.findAll());
					break;
				case '7':     // Exibir todos que contem determinado nome
					String nome = JOptionPane.showInputDialog("Nome");
					listaFuncionarios(baseFuncionarios.buscaPorNomeIniciadoPor(nome));
					break;
				case '8':     // Sair
					JOptionPane.showMessageDialog(null, "Obrigado pela visita, até a próxima!");
					break;
				default:
					JOptionPane.showMessageDialog(null, "Opção Inválida");
					break;
			}
		} while(opcao != '8');
	}
}
