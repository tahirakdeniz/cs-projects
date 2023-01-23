import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Scanner;

public class FileIO {


    private static String readFilePath;

    public static void setReadFilePath(String readFilePath) {
        FileIO.readFilePath = readFilePath;
    }

    public static Collection<Contact> readFileTo(Collection<Contact> collection) {
        File file = new File(readFilePath);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){
                String[] arr = scanner.nextLine().trim().split(" ");
                Contact contact = new Contact(arr);

                collection.add(contact);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return collection;
    }

    public static HashMap<String, Contact> readFileTo(HashMap<String, Contact> hashMap){
        File file = new File(readFilePath);

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()){

                String[] arr = scanner.nextLine().trim().split(" ");
                Contact contact = new Contact(arr);

                hashMap.put(contact.getPhoneNumber(), contact);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return hashMap;
    }

    public static void writeOutput(Collection<Contact> collection, String outputFileName) {
        File file = new File(outputFileName);
        try {

            FileWriter fileWriter = new FileWriter(file);

            for (Contact contact : collection)
                fileWriter.write(contact.toString());

            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
