package list6.UI;

import list6.DAO.FuncionarioDAO;
import list6.DAO.FuncionarioJPADAO;
import list6.MODEL.Funcionario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class main implements CommandLineRunner {

	FuncionarioDAO funcionarioDAO = new FuncionarioJPADAO();

	public static void main(String[] args) {
		SpringApplicationBuilder builder = new SpringApplicationBuilder(main.class);
		builder.headless(false).run(args);
	}

	public void listaFuncionarios() throws SQLException{
		List<Funcionario> funcionarios = funcionarioDAO.buscarTodos();

		StringBuilder lista = new StringBuilder();
		for(Funcionario funcionario : funcionarios) {
			lista.append(funcionario).append("\n");
		}
		JOptionPane.showMessageDialog(null, lista.length() == 0 ? "Nenhum funcionario encontrado" : lista);

	}

	public void listaUmFuncionario(Funcionario funcionario){
		JOptionPane.showMessageDialog(null, funcionario == null ? "Nenhum funcionario encontrado" : funcionario);
	}

	public void adicionarDados(Funcionario funcionario){
		String cpf = JOptionPane.showInputDialog("Cpf");
		String matricula = JOptionPane.showInputDialog("Matricula");
		String nome = JOptionPane.showInputDialog("Nome");
		String email = JOptionPane.showInputDialog("Email");
		String telefone = JOptionPane.showInputDialog("Telefone");

		funcionario.setCpf(cpf);
		funcionario.setMatricula(matricula);
		funcionario.setNome(nome);
		funcionario.setEmail(email);
		funcionario.setTelefone(telefone);
	}

	@Override
	public void run(String... args) throws Exception {
		String menu = "Olá, seja Bem-vindo!\n" +
				"Escolhar uma opção:\n\n" +
				"1 - Adicionar um novo Funcionário\n" +
				"2 - Deletar um Funcionário\n" +
				"3 - Editar dados de um Funcionário\n" +
				"4 - Buscar um Funcionário pelo seu Id\n" +
				"5 - Listar todos os Funcionários\n\n" +
				"6 - Sair do menu\n";
		char escolha = '0';

		while(escolha != '6'){
			escolha = JOptionPane.showInputDialog(menu).charAt(0);
			if(escolha == '1'){ //Inserir um funcionário.
				Funcionario funcionario = new Funcionario();
				adicionarDados(funcionario);
				funcionarioDAO.adicionar(funcionario);
			}
			if(escolha == '2'){ //Deletar um funcionário.
				int id = Integer.parseInt(JOptionPane.showInputDialog("id"));
				funcionarioDAO.deletar(id);
			}
			if(escolha == '3'){ //Editar um funcionário.
				Funcionario funcionario = new Funcionario();
				int id = Integer.parseInt(JOptionPane.showInputDialog("id"));
				funcionario.setId(id);
				adicionarDados(funcionario);
				funcionarioDAO.editar(funcionario);
			}
			//Buscar apenas um funcionário
			if(escolha == '4'){
				int id = Integer.parseInt(JOptionPane.showInputDialog("id"));
				Funcionario funcionario = funcionarioDAO.buscar(id);
				listaUmFuncionario(funcionario);
			}
			//Lista todos os funcionarios
			if(escolha == '5') {
				listaFuncionarios();
			}
			if (escolha == '6'){
				System.out.println("Até mais, obrigado pela visita!");
			}
		}
	}
}

