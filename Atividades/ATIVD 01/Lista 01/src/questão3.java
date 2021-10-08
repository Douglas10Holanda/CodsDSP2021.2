
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class questão3 {

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do Primeiro arquivo Origem...");
        String arqo = scan.nextLine();
        System.out.println("\n");
        System.out.println("Digite o nome do Segundo arquivo Destino...");
        String arqd = scan.nextLine();
        System.out.println("\n");

        InputStream ler_arq1 = new FileInputStream(arqo);
        Scanner text_arq1 = new Scanner(ler_arq1);

        PrintStream ps = new PrintStream(arqd);
        long tempo = System.currentTimeMillis(); // Retorna a diferença entre a hora atual e a meia noite

        while (text_arq1.hasNextLine()) {
            if (text_arq1.hasNextLine()) {
                System.out.println(text_arq1.nextLine());
            }
        }

        // Iremos mostrar o tempo total da cópia
        long tempo_total = System.currentTimeMillis() - tempo;

        System.out.println(
                "\n O tempo total em segundos foi de: " + tempo_total);
    }
}
