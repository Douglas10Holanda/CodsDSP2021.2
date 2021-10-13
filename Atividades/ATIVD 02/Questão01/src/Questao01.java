
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.Scanner;

public class Questao01 {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner scan = new Scanner(System.in);
        String arqOrigem, arqDestino;

        System.out.println("Digite o nome do arquivo ORIGEM, e lembre-se de por .txt no final");
        arqOrigem = scan.next();

        File arquivo = new File(arqOrigem);

        long inicial = System.currentTimeMillis();
        byte[] bytes = Files.readAllBytes(arquivo.toPath());

        String textOrigem = new String(bytes, "UTF-8");

        System.out.println("\n");
        System.out.println("\nDigite o nome do arquivo DESTINO e lembre-se de por .txt no final");
        arqDestino = scan.next();

        try (PrintWriter pw = new PrintWriter(arqDestino)) {
            pw.print(textOrigem);

            long tempo = System.currentTimeMillis() - inicial;
            System.out.println("\nO tempo total da cópia foi de: " + tempo + " ms");
        }

    }

}
