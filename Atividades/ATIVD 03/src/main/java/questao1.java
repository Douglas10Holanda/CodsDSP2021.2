import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

public class questao1 {
    public static void main(String[] args) throws IOException {
        int line_inicial = 0 ;
        int line_final;

        Properties config = new Properties();
        config.setProperty("linha_inicial", "2");
        config.setProperty("linha_final", "6");

        // Gravando
        config.store(new FileOutputStream("config.properties"), null);

        // Lendo
        config.load(new FileInputStream("config.properties"));

        line_inicial = Integer.parseInt(config.getProperty("linha_inicial"));
        line_final = Integer.parseInt(config.getProperty("linha_final"));

        Scanner scan = new Scanner(System.in);

        String arquivo = scan.nextLine();
        InputStream is = new FileInputStream(arquivo);

        Scanner scanner = new Scanner(is);
        for (int i=1;i < line_final;i++){
            if (i < line_inicial){
                scanner.nextLine();
            } else {
                System.out.println(scanner.nextLine());
            }
        }
        is.close();
    }
}
