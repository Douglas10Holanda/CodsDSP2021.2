package trabalho;

import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class cadastrar {
    public static void main(String[] args) throws IOException, CsvDataTypeMismatchException, CsvRequiredFieldEmptyException {
        int ler = 0; // contador de parada do laço

        List<Filme> filmes = new ArrayList(); // criação de uma lista de filmes

        Scanner scan = new Scanner(System.in); // Scanner para ler valor do teclado

        // laço para ler os valores pelo usuario na linha de comando
        while (ler != -1) {
            System.out.println("-----Digite o nome do Filme-----");
            String nome = scan.nextLine();
            System.out.println("\n");
            System.out.println("-----Digite o nome do genero do Filme-----");
            String genero = scan.nextLine();
            System.out.println("\n");
            System.out.println("-----Digite ano de lançamento do Filme-----");
            String ano_lancamento = scan.nextLine();
            System.out.println("\n");
            System.out.println("-----Digite a nota do Filme-----");
            String nota = scan.nextLine();
            System.out.println("\n");
            System.out.println("-----Deseja parar de adicionar filmes?-----");
            System.out.println("--- SIM(1) --- NÃO(2) ---");

            // Saír ou continuar no laço
            int parar = scan.nextInt();
            if (parar == 1){
                ler = -1;
                System.out.println(parar + " - O usuário desejou parar de adiconar filmes... \n");
            } else {
                System.out.println(parar + " - 0 usuario vai continuar adicionando filmes... \n");
            }

            // para adicionar filmes digitado pelo usuario a lista
            filmes.add(new Filme(nome, genero, ano_lancamento, nota));
            nome = scan.nextLine(); // Continuar lendo a linha nome do filme
        }

        // Escrever a lista de filmes no arquivo csv
        BufferedWriter writer = new BufferedWriter(new FileWriter("Lista.csv", true));
        StatefulBeanToCsv<Filme> beanToCsv = new StatefulBeanToCsvBuilder(writer).build();
        beanToCsv.write(filmes);
        writer.flush();
        writer.close();
    }
}