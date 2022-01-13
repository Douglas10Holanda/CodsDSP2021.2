package UNIVERSIDADE.APLICACAO.CONTROLLER;

import UNIVERSIDADE.APLICACAO.DAO.AlunoDAO;
import UNIVERSIDADE.APLICACAO.DAO.DisciplinaDAO;
import UNIVERSIDADE.APLICACAO.ENTITY.Disciplina;
import UNIVERSIDADE.APLICACAO.ENTITY.ENTITY2.nome_email;
import UNIVERSIDADE.APLICACAO.UI.View;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import UNIVERSIDADE.APLICACAO.ENTITY.Aluno;
import UNIVERSIDADE.APLICACAO.ENTITY.ENTITY2.nome_numeroDisciplinas;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ComponentScan("UNIVERSIDADE.APLICACAO")
public class Metodos implements CommandLineRunner {
    @Autowired
    private AlunoDAO alunoDAO;
    @Autowired
    private DisciplinaDAO disciplinaDAO;

    Aluno aluno2;
    Disciplina disciplina2;
    List<Aluno> Lista_alunos;
    List<Disciplina> Lista_disciplinas;
    SimpleDateFormat formato = new SimpleDateFormat("dd/mm/yyyy");

    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(Metodos.class);
        springApplicationBuilder.headless(false).run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        String opcao = null;

        try {
            opcao = JOptionPane.showInputDialog(null,
                    "Olá, seja bem-vindo!\n" +
                            "Escolha uma das opções abaixo:\n\n " +
                            "  1 - Adicionar aluno\n" +
                            "  2 - Adicionar disciplina\n" +
                            "  3 - Associar disciplina ao aluno\n" +
                            "  4 - Editar aluno\n" +
                            "  5 - Editar disciplina\n" +
                            "  6 - Apagar aluno\n" +
                            "  7 - Apagar disciplina\n" +
                            "  8 - Listar todos os alunos\n" +
                            "  9 - Listar todas as disciplinas\n" +
                            " 10 - Listar nome e as disciplinas de determinado aluno\n" +
                            " 11 - Listar os alunos que fazem parte de alguma disciplina\n" +
                            " 12 - Listar os alunos e a quantidade de disciplinas\n" +
                            " 13 - Listar a matricula, nome e o email de um aluno\n" +
                            " 14 - Listar os alunos que nasceram depois de determinada data\n\n" +
                            " 15 - SAIR\n",
                    "Menu", JOptionPane.QUESTION_MESSAGE);
            if (opcao.equalsIgnoreCase("1")) {
                adicionarAluno();
            } else if (opcao.equalsIgnoreCase("2")) {
                adicionarDisciplina();
            } else if (opcao.equalsIgnoreCase("3")) {
                associarDisciplinaAoAluno();
            } else if (opcao.equalsIgnoreCase("4")) {
                editarAluno();
            } else if (opcao.equalsIgnoreCase("5")) {
                editarDisciplina();
            } else if (opcao.equalsIgnoreCase("6")) {
                deleteAluno();
            } else if (opcao.equalsIgnoreCase("7")) {
                deleteDisciplina();
            } else if (opcao.equalsIgnoreCase("8")) {
                listarAlunos();
            } else if(opcao.equalsIgnoreCase("9")) {
                listarDisciplinas();
            } else if(opcao.equalsIgnoreCase("10")) {
                primeiroNome();
            } else if(opcao.equalsIgnoreCase("11")) {
                listaAlunoPorCod();
            } else if(opcao.equalsIgnoreCase("12")) {
                alunoQuantDiscBC();
            } else if(opcao.equalsIgnoreCase("13")) {
                listaNomeEemail();
            } else if(opcao.equalsIgnoreCase("14")) {
                listaAlunoData();
            } else if(opcao.equalsIgnoreCase("15")) {
                JOptionPane.showMessageDialog(null, "Até a próxima! :)", "Sair", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida, tente novamente!", "Opção inválida", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NullPointerException x) {
            JOptionPane.showMessageDialog(null, "Tá com pressa? Falou :D", "Cancelar", JOptionPane.ERROR_MESSAGE);
            opcao = "exit";
        } catch (RuntimeException y) {
            JOptionPane.showMessageDialog(null, y.toString());
        }
    }

    // Função para adicionar disciplinas
    public void adicionarDisciplina() {
        String nome = View.nomeDisciplina();
        String codigo = View.codigoDisciplina();
        disciplina2 = disciplinaDAO.findByCodigo(Integer.parseInt(codigo));

        if (disciplina2 != null) {
            throw new RuntimeException("Tente outro código");
        }

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(nome);
        disciplina.setCodigo(Integer.parseInt(codigo));
        disciplinaDAO.save(disciplina);
    }

    // Função para adicionar alunos
    public void adicionarAluno() throws ParseException {
        String cpf = JOptionPane.showInputDialog(null, "CPF");
        String matricula = JOptionPane.showInputDialog(null, "Matrícula");
        String nome = JOptionPane.showInputDialog(null, "Nome");
        String email = JOptionPane.showInputDialog(null, "Email");
        String data = JOptionPane.showInputDialog(null, "Data de Nascimento");
        aluno2 = alunoDAO.findByCpf(cpf);

        if(aluno2 != null) {
            throw new RuntimeException("Tente outro Aluno");
        }

        Aluno aluno = new Aluno();
        aluno.setCpf(cpf);
        aluno.setNome(nome);
        aluno.setMatricula(Integer.parseInt(matricula));
        Date dataDefinida = formato.parse(data);
        aluno.setDataNasc(dataDefinida);
        aluno.setEmail(email);
        alunoDAO.save(aluno);
    }

    // Função para associar uma disciplina a um aluno
    public void associarDisciplinaAoAluno(){
        String aluno = View.idAluno();
        String disciplina = View.idDisciplina();
        disciplina2 = disciplinaDAO.findDisciplina(Integer.parseInt(disciplina));
        aluno2 = alunoDAO.findAluno(Integer.parseInt(aluno));
        disciplina2.getAlunos().add(aluno2);

        if (disciplina2 == null || aluno2 == null) {
            throw new RuntimeException("Não encontrado");
        }
        disciplinaDAO.save(disciplina2);
    }

    // Função para apagar um aluno
    public void deleteAluno(){
        String idAluno = View.idAluno();

        if(!alunoDAO.findById(Integer.parseInt(idAluno)).isPresent()) {
            throw new RuntimeException("Aluno não encontrado");
        }

        alunoDAO.deleteById(Integer.parseInt(idAluno));
    }

    // Função para apagar uma disciplina
    public void deleteDisciplina(){
        String idDisciplina = View.idDisciplina();

        if(!disciplinaDAO.findById(Integer.parseInt(idDisciplina)).isPresent()) {
            throw new RuntimeException("Disciplina não encontrada");
        }

        disciplinaDAO.deleteById(Integer.parseInt(idDisciplina));
    }

    // Função para editar os dados de um aluno
    public void editarAluno() throws ParseException {
        String aluno = View.idAluno();
        aluno2 = alunoDAO.findAluno(Integer.parseInt(aluno));

        if (!alunoDAO.findById(Integer.parseInt(aluno)).isPresent()) {
            throw new RuntimeException("Aluno não encontrado");
        }

        buscarAluno(aluno2);
        alunoDAO.save(aluno2);
    }

    // Função para editar os dados de uma disciplina
    public void editarDisciplina() {
        String disciplina = View.idDisciplina();
        disciplina2 = disciplinaDAO.findDisciplina(Integer.parseInt(disciplina));

        if (!disciplinaDAO.findById(Integer.parseInt(disciplina)).isPresent()) {
            throw new RuntimeException("Disciplina não encontrada");
        }

        buscarDisciplina(disciplina2);
        disciplinaDAO.save(disciplina2);
    }

    // Função para buscar um aluno
    public void buscarAluno(Aluno aluno) throws ParseException {
        String cpf = JOptionPane.showInputDialog(null, "CPF", aluno.getCpf());
        String matricula = JOptionPane.showInputDialog(null, "Matrícula", aluno.getMatricula());
        String nome = JOptionPane.showInputDialog(null, "Nome", aluno.getNome());
        String email = JOptionPane.showInputDialog(null, "Email", aluno.getEmail());
        String data = JOptionPane.showInputDialog(null, "Data de Nascimento", aluno.getDataNasc());

        aluno.setCpf(cpf);
        aluno.setMatricula(Integer.parseInt(matricula));
        aluno.setEmail(email);
        aluno.setNome(nome);
        Date dataDefinida = formato.parse(data);
        aluno.setDataNasc(dataDefinida);
    }

    // Função para buscar uma disciplina
    public void buscarDisciplina(Disciplina disciplina){
        String nome = JOptionPane.showInputDialog(null, "nome", disciplina.getNome());
        String cod = JOptionPane.showInputDialog(null, "codigo", disciplina.getCodigo());

        disciplina.setNome(nome);
        disciplina.setCodigo(Integer.parseInt(cod));
    }

    // Função para listar todos os alunos
    public void listarAlunos(){
        Lista_alunos = alunoDAO.findAll();
        StringBuilder stringBuilder = new StringBuilder();

        for (Aluno aluno : Lista_alunos) {
            stringBuilder.append("ID: ").append(aluno.getId()).append(", Matricula: ").append(aluno.getMatricula())
                    .append(", Nome: ").append(aluno.getNome())
                    .append(", Email: ").append(aluno.getEmail()).append(", CPF: ")
                    .append(aluno.getCpf()).append(", Disciplinas: ").append(aluno.getDisciplinas()).append("\n");
        }

        JOptionPane.showMessageDialog(null, stringBuilder.toString());
        Lista_alunos.clear();
    }

    // Função para listar todas as disciplinas
    public void listarDisciplinas(){
        Lista_disciplinas = disciplinaDAO.findAll();
        JOptionPane.showMessageDialog(null, Lista_disciplinas.toString());
        Lista_disciplinas.clear();
    }

    // Função para pesquisar uma aluno por determinado nome
    public void primeiroNome() {
        String palavra = JOptionPane.showInputDialog(null, "Qual é o primeiro nome?");
        Lista_alunos = alunoDAO.findNomeAluno(palavra);
        StringBuilder stringBuilder = new StringBuilder();

        for (Aluno aluno : Lista_alunos) {
            stringBuilder.append("Nome: ").append(aluno.getNome()).append(", Disciplinas:\n").append("  ")
                    .append(aluno.getDisciplinas()).append("\n");
        }

        JOptionPane.showMessageDialog(null, stringBuilder.toString());
        Lista_alunos.clear();
    }

    // Função para listar os alunos que fazem parte de alguma disciplina pelo codigo dela
    public void listaAlunoPorCod() {
        String string = View.codigoDisciplina();
        Lista_alunos = disciplinaDAO.findCod(Integer.parseInt(string));

        if (Lista_alunos.get(0) == null) {
            throw new RuntimeException("Aluno não encontrado");
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Código da Disciplina:  ").append(string).append("\n\n");

        for (Aluno aluno : Lista_alunos) {
            stringBuilder.append("\tNome: ").append(aluno.getNome()).append(", CPF: ").append(aluno.getCpf())
                    .append(", Matricula: ").append(aluno.getMatricula()).append(", Data de Nascimento: ").append(aluno.getDataNasc()).append("\n");
        }

        JOptionPane.showMessageDialog(null, stringBuilder.toString());
        Lista_alunos.clear();
    }

    // Função para listar o nome do aluno e a quantidade de disciplinas cursadas pelo aluno
    public void alunoQuantDiscBC(){
        List<nome_numeroDisciplinas> lista = (List<nome_numeroDisciplinas>) alunoDAO.findAlunoNumeroDisciplinas();
        StringBuilder stringBuilder = new StringBuilder();

        for (nome_numeroDisciplinas aluno : lista) {
            stringBuilder.append("Nome do aluno: ").append(aluno.getNome()).append(", Número de Disciplinas: ")
                    .append(aluno.getCount()).append("\n");
        }

        JOptionPane.showMessageDialog(null, stringBuilder.toString());
    }

    // Função para listar o nome e o email de um aluno pela sua matricula
    public void listaNomeEemail(){
        String matricula = View.alunoMatricula();
        List<nome_email> lista = (List<nome_email>) alunoDAO.findAlunoCodMatricula(Integer.parseInt(matricula));
        StringBuilder stringBuilder = new StringBuilder();

        for (nome_email aluno : lista) {
            stringBuilder.append("Nome: ").append(aluno.getNome()).append(", Email: ").append(aluno.getEmail()).append("\n");
        }

        JOptionPane.showMessageDialog(null, stringBuilder.toString());
        lista.clear();
    }

    // Função para listar alunos nascido em datas superiores a data escolhida
    public void listaAlunoData() throws ParseException {
        String dataDeNascimento = View.dataDeNascimento();
        Date dataDefinida = formato.parse(dataDeNascimento);
        Lista_alunos = alunoDAO.findAlunoData(dataDefinida);
        StringBuilder stringBuilder = new StringBuilder();

        for (Aluno aluno : Lista_alunos) {
            stringBuilder.append("ID: ").append(aluno.getId()).append(", Nome: ").append(aluno.getNome()).append(", Data de Nascimento: ").append(aluno.getDataNasc())
                    .append("\n");
        }

        JOptionPane.showMessageDialog(null, stringBuilder.toString());
        Lista_alunos.clear();
    }
}