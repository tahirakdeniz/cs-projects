public class Main {
    public static void main(String[] args) {

        // FINAL:
        final String PERSON_FILE_PATH = "people.txt";
        final String FOOD_FILE_PATH = "food.txt";
        final String SPORT_FILE_PATH = "sport.txt";
        final String COMMAND_FILE_PATH = args[0];
        final String OUTPUT_FILE_PATH = "monitoring.txt";

        // Read files and create People, Food, Sport instances
        PersonController.readPersonFile(PERSON_FILE_PATH);
        FoodController.readFoodFile(FOOD_FILE_PATH);
        SportController.readSportFile(SPORT_FILE_PATH);

        // Create Command Object
        Command command = new Command(COMMAND_FILE_PATH, OUTPUT_FILE_PATH);
        command.runCommands();
    }
}
