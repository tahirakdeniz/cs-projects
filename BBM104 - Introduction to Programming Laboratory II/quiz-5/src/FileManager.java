import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {

    private Scanner scanner;
    private FileWriter fileWriter;
    private File file;

    FileManager(String filePath){
        file = new File(filePath);
        try {
            scanner = new Scanner(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean hasNextLine(){
        boolean b = scanner.hasNextLine();
        return b;
    }

    public String nextLine(){
        return scanner.nextLine();
    }

    public void write(String string) {
        try {
        if (fileWriter == null){
            file.createNewFile();
            fileWriter = new FileWriter(file);
        }
            fileWriter.write(string + '\n');
            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
