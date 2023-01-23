public class Main {
    public static void main(String[] args) {
       final String INPUT_FILE_PATH = args[0];
       final String OUTPUT_FILE_PATH = "output.txt";

       FileManager inputFileManager = new FileManager(INPUT_FILE_PATH);
       FileManager outputFileManager = new FileManager(OUTPUT_FILE_PATH);

       Application application = new Application(inputFileManager, outputFileManager);
       application.run();
    }
}
