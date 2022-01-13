package UNIVERSIDADE.APLICACAO.UI;

import javax.swing.*;
public class View {
    public static String idAluno(){
        return JOptionPane.showInputDialog(null, "ID do Aluno", "ID", JOptionPane.QUESTION_MESSAGE);
    }
    public static String idDisciplina(){
        return JOptionPane.showInputDialog(null, "ID da Disciplina", "ID", JOptionPane.QUESTION_MESSAGE);
    }
    public static String dataDeNascimento(){
        return JOptionPane.showInputDialog(null, "Data de Nascimento", "Date", JOptionPane.QUESTION_MESSAGE);
    }
    public static String codigoDisciplina(){
        return JOptionPane.showInputDialog(null, "Código da disciplina", "Código", JOptionPane.QUESTION_MESSAGE);
    }
    public static String nomeDisciplina(){
        return JOptionPane.showInputDialog(null, "Nome da disciplina", "nome", JOptionPane.QUESTION_MESSAGE);
    }
    public static String alunoMatricula(){
        return JOptionPane.showInputDialog(null, "Matrícula do Aluno", "Matrícula", JOptionPane.QUESTION_MESSAGE);
    }
}