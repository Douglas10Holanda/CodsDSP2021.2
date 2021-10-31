import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Serializa {
    public static void main(String[] args) throws IOException {

        List<Pessoa> p = new ArrayList();
        p.add(new Pessoa(01,"Douglas Holanda", "douglaskk@gmail.com", 889888383));
        p.add(new Pessoa(02, "Dirlandia Oliver", "di@gmail.com", 988888888));

        try {
            FileOutputStream fileOut =
                    new FileOutputStream("pessoas.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(p);
            out.close();
            fileOut.close();
            System.out.printf("Serializado");
        }catch(IOException i) {
            i.printStackTrace();
        }

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("target/pessoas.json"), p);

        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.writeValue(new File("target/pessoas.xml"), p);
        File file = new File("pessoas.xml");
    }
}
