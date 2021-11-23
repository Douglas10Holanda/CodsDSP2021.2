package list5;

import list5.DAO.FuncionarioDAO;
import list5.MODEL.Funcionario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner {
    @Autowired
    FuncionarioDAO funcionarioDAO;

    public static void main(String[] args){
        SpringApplicationBuilder builder = new SpringApplicationBuilder(Application.class);
        builder.headless(false).run(args);
    }

    public void listaFuncionarios(List<Funcionario> funcionarios) {
        List<String> fun = new ArrayList<>();

        for(Funcionario funcionario : funcionarios) {
            String valor =
                    "\nId: "+ funcionario.getId()+
                            "\nNome: "+ funcionario.getNome()+
                            "\nMatrícula: "+ funcionario.getMatricula()+
                            "\nCpf: " + funcionario.getCpf()+
                            "\nEmail: "+ funcionario.getEmail() +
                            "\nTelefone: "+funcionario.getTelefone()+"\n";

            String separator ="===================";
            fun.add(valor);
            fun.add(separator);
        }
        JOptionPane.showMessageDialog(null,fun);
    }

    public Funcionario retornaFuncionario(int id) throws SQLException {
        return funcionarioDAO.buscar(id);
    }

    public void listaFuncionario(int id) throws SQLException {
        Funcionario funcionario = funcionarioDAO.buscar(id);
        if(funcionario != null){
            String valor =
                    "\nId: "+ funcionario.getId()+
                            "\nNome: "+ funcionario.getNome()+
                            "\nMatrícula: "+ funcionario.getMatricula()+
                            "\nCpf: " + funcionario.getCpf()+
                            "\nEmail: "+ funcionario.getEmail() +
                            "\nTelefone: "+funcionario.getTelefone()+"\n";
            JOptionPane.showMessageDialog(null,  valor);
        }
    }

    public static void inserir(Funcionario funcionario){
        String cpf = JOptionPane.showInputDialog("CPF", funcionario.getCpf());
        String matricula = JOptionPane.showInputDialog("Matricula", funcionario.getMatricula());
        String nome = JOptionPane.showInputDialog("Nome", funcionario.getNome());
        String email = JOptionPane.showInputDialog("Email", funcionario.getEmail());
        String telefone = JOptionPane.showInputDialog("Telefone", funcionario.getTelefone());

        if (cpf!=null)
            funcionario.setCpf(cpf);
        if (matricula!=null)
            funcionario.setMatricula(matricula);
        if (nome!=null)
            funcionario.setNome(nome);
        funcionario.setEmail(email);
        funcionario.setTelefone(telefone);
    }

    @Override
    public  void run(String... args) throws Exception {
        String menu = """
                Olá, Seja bem-vindo!\n
                Escolha uma opção:\n
                 1 - Adicionar um novo Funcionário
                 2 - Deletar um Funcionário
                 3 - Editar dados de um Funcionário
                 4 - Buscar um Funcionário pelo seu Id
                 5 - Listar todos os Funcionários
                 6 - Sair do menu\n""";

        char escolha = '0';
        while (escolha!='6'){
            Funcionario funcionario;
            escolha = JOptionPane.showInputDialog(menu).charAt(0);
            switch (escolha) {
                // Adicionar funcionario do banco
                case '1' -> {
                    funcionario = new Funcionario();
                    inserir(funcionario);
                    funcionarioDAO.adicionar(funcionario);
                }
                // Excluir um funcionario do banco
                case '2' -> {
                    int id;
                    id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
                    funcionarioDAO.deletar(id);
                }
                // Atualiza os dados de um funcionario no banco
                case '3' -> {
                    funcionario = new Funcionario();
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
                    funcionario.setId(id);
                    inserir(funcionario);
                    funcionarioDAO.editar(funcionario);
                }
                // Busca um funcionario cadastrado no banco pelo seu id
                case '4' -> {
                    int id = Integer.parseInt(JOptionPane.showInputDialog("Id"));
                    listaFuncionario(id);
                }
                // Lista todos os funcionarios cadastrados no banco
                case '5' -> {
                    listaFuncionarios(funcionarioDAO.buscar());
                }
                default -> {
                    System.out.println("Até mais, obrigado pela visita!");
                }
            }
        }
    }
}