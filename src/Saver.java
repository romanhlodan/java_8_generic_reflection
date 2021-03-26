import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Saver <E>{
    public  void save(File file, E integerBooleanPerson) throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.println(integerBooleanPerson);
        printWriter.close();
    }
}
