import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String inputFilePath = args[0];
        File inputFile = new File(inputFilePath);
        Scanner inputScanner = null;

        try {
            inputScanner = new Scanner(inputFile);
        } catch (FileNotFoundException e) {
            System.err.println("file doesn't exist");
            System.exit(0);
        }

        while (inputScanner.hasNextLine()) {
            Match match = new Match(inputScanner.nextLine());
            Sport.readMatch(match);;
        }

        Sport.writeResults();
    }
}

