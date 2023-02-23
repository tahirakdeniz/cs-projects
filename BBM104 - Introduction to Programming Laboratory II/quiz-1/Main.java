import java.io.File;
import java.io.FileWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.lang.Math;

public class Main {

    public static void main(String[] args) throws Exception {
        // Get paths from command line arguments:
        String operationFilePath = args[0];
        String outputFilePath = args[1];

        // Get file to read
        File operationFile = new File(operationFilePath);
        Scanner operationScanner = new Scanner(operationFile);

        // Create output file
        File outputFile = new File(outputFilePath);
        outputFile.createNewFile();

        // Get output file to read
        FileWriter outputFileWriter = new FileWriter(outputFilePath);

        // Read line by line
        while (operationScanner.hasNextLine()) {
            String line = operationScanner.nextLine();

            if ("Calculating Armstrong numbers".equals(line))
            {
                outputFileWriter.write("Calculating Armstrong numbers\n");
                calculateArmstrongNumbers(operationScanner, outputFileWriter);
            }

            else if ("Ascending order sorting".equals(line))
            {
                outputFileWriter.write("Ascending order sorting\n");
                Sorting(operationScanner, true, outputFileWriter);
            }

            else if ("Descending order sorting".equals(line))
            {
                outputFileWriter.write("Descending order sorting\n");
                Sorting(operationScanner, false, outputFileWriter);
            }
        }

        outputFileWriter.write("Terminated..");
        outputFileWriter.close();

    }

    static void calculateArmstrongNumbers(Scanner operationScanner, FileWriter outputFileWriter) throws Exception {
        // reads next line and calculate Armstrong numbers up to read number.

        int upToNumber = Integer.parseInt(operationScanner.nextLine());

        for (int i = 100; i<=upToNumber; i++) {
            int length = (int) Math.log10(i) + 1;

            int number = i;
            int sum = 0;

            // Find digits of given number and calculate sum of these digits:
            while (number > 0) {
                int digit = number%10;
                sum += Math.pow(digit, length);
                number = (number - digit)/10;
            }

            if (i == sum) {
                outputFileWriter.write(i + " ");
            }
        }

        outputFileWriter.write("\n");

    }

    static void Sorting(Scanner operationScanner, boolean isAscending, FileWriter outputFileWriter) throws Exception {
        Integer[] oldArr = new Integer[0];
        int length = 0;
        int numberInLine = Integer.parseInt(operationScanner.nextLine());

        while (numberInLine != -1){
            // Create newArr that contains all of oldArr
            Integer[] newArr = new Integer[++length];

            for (int i = 0; i < length; i++){
                if (i == length - 1) {
                    newArr[i] = numberInLine;
                }
                else newArr[i] = oldArr[i];
            }

            if (isAscending) {
                Arrays.sort(newArr);
            }
            else {
                Arrays.sort(newArr, Collections.reverseOrder());
            }

            for (int i: newArr)
                outputFileWriter.write(i + " ");

            outputFileWriter.write("\n");
            oldArr = newArr;
            numberInLine = Integer.parseInt(operationScanner.nextLine());
        }
    }

}
