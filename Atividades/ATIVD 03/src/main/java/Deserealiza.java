import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class Deserealiza {
    public static void main(String [] args) throws IOException ,ClassNotFoundException{
        List<String> pessoas;
        try {
            FileInputStream fileIn = new FileInputStream("pessoas.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            pessoas = (List<String>) in.readObject();
            System.out.println(pessoas);
            in.close();
            fileIn.close();
        }catch(Exception ex) {
            ex.printStackTrace();
        }
    }
}
