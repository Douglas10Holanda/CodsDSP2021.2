
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class questão01 {

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        System.out.println("Digite o nome do arquivo...");
        String nome = scan.nextLine(); // Pega o nome do arquivo que o usuario digitar
        System.out.println("\n");
        System.out.println("Digite a substring...");
        String subs = scan.nextLine(); // Pega a Substring que o usuario digitar
        System.out.println("\n");
        // InputStream é um fluxo de entrada para ler bytes
        InputStream arq = new FileInputStream(nome);
        Scanner ler = new Scanner(arq);

        while (ler.hasNextLine()) {
            String linha = ler.nextLine();
            if (linha.contains(subs)) { // subs é a substring 
                System.out.println(linha); // Imprimir a linha que contém a substring
            }
        }
        arq.close();
    }
}
