
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class questão02 {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do Primeiro arquivo...");
        String arq1 = scan.nextLine();
        System.out.println("\n");
        System.out.println("Digite o nome do Segundo arquivo...");
        String arq2 = scan.nextLine();
        System.out.println("\n");
        System.out.println("Digite o nome do Terceiro arquivo...");
        String arq3 = scan.nextLine();
        System.out.println("\n");

        InputStream ler_arq1 = new FileInputStream(arq1);
        InputStream ler_arq2 = new FileInputStream(arq2);

        Scanner text_arq1 = new Scanner(ler_arq1);
        Scanner text_arq2 = new Scanner(ler_arq2);

        PrintStream ps = new PrintStream(arq3);
        long tempo = System.currentTimeMillis(); // Retorna a diferença entre a hora atual e a meia noite

        while (text_arq1.hasNextLine() || text_arq2.hasNextLine()) {
            if (text_arq1.hasNextLine()) {
                System.out.println(text_arq1.nextLine());
            } else {
                System.out.println(text_arq2.nextLine());
            }
        }

        // Iremos mostrar o tempo total da cópia
        long tempo_total = System.currentTimeMillis() - tempo;
        System.out.println("\n O tempo total em segundos foi de: " + tempo_total);

        //Iremos mostrar o tamanho do arquivo gerado
        Path tamanho = Paths.get(
                "C:\\Users\\dougl\\Documents\\FACULDADE\\6º Semestre\\Desenvolvimento de Software para Persistência\\CÓDIGOS JAVA\\Questão02"
                + arq3);
        byte[] bytes = Files.readAllBytes(tamanho);
        System.out.println("\n O tamanho do arquivo criado foi de: " + bytes.length);
    }
}
