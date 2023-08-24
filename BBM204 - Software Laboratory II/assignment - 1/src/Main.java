public class Main {
    private final static int[] INPUT_SIZES = {500, 1000, 2000, 4000, 8000, 16000, 32000, 64000, 128000, 250000};

    public static void main(String[] args) {
        String csvFile = args[0];
        App app = new App(INPUT_SIZES, csvFile);
        app.run();
    }
}
