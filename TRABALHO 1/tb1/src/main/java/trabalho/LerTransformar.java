package trabalho;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LerTransformar {
    public static void main(String[] args) throws IOException {
        // Ler dados do arquivo CSV
        Files.lines(Paths.get("Lista.csv"))
                .skip(1) // remove a linha do cabeçalho
                .forEach(System.out::println);

        // Tranformar em XML
        // Não consegui pegar os dados e colocar dentro do arquivo XML
        Object xstream = "Não consegui colocar os dados do CSV no XML";
        String myXML = xstream.toString();
        BufferedWriter writer = new BufferedWriter(new FileWriter("Lista.xml"));
        writer.write(myXML); //salva fisicamente
        writer.close();
        System.out.println("\n-----Arquivo XML criado com sucesso!-----");

        // Transformar em JSON
        // Não consegui transformar em JSON
    }
}