import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class to create needed objects to read file or write to file by giving path.
 * */
public abstract class FileCreator {

    private final File file;
    private FileWriter fileWriter;
    private Scanner scanner;

    public FileCreator(String filePath) {
        file = new File(filePath);
    }

    public Scanner getScanner() {

        if (scanner == null) {
            try {
                scanner = new Scanner(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        return scanner;
    }

    public FileWriter getFileWriter() {

        if (fileWriter == null) {
            try {
                fileWriter = new FileWriter(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileWriter;
    }

}
