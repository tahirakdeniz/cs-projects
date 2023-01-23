import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class PersonController {

    private final static HashMap<String, Person> PERSON_HASH_MAP = new HashMap<>();

    // Public Static Methods:
    public static void readPersonFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) new Person(fileScanner.nextLine().split("\t"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void increaseCaloriesBurned(String personId, int caloriesBurned) {
        PERSON_HASH_MAP.get(personId).totalCaloriesBurned += caloriesBurned;
    }

    public static void increaseCaloriesTaken(String personId, int caloriesTaken) {
        PERSON_HASH_MAP.get(personId).totalCaloriesTaken += caloriesTaken;
    }

    public static String outputStringById(String personId) {
        Person person = PERSON_HASH_MAP.get(personId);
        String output =
                person.NAME + "\t"
                + person.AGE + "\t"
                + person.DAILY_CALORIE_NEEDS + "kcal\t"
                + person.totalCaloriesTaken + "kcal\t"
                + person.totalCaloriesBurned + "kcal\t";

        if (isWarn(personId)) {
            output += "+" + calculateNetCalorie(person) + "kcal\n";
        }
        else output += calculateNetCalorie(person) + "kcal\n";
        return output;
    }

    public static boolean isWarn(String personId) {
        return calculateNetCalorie(PERSON_HASH_MAP.get(personId)) > 0;
    }
    // --- Public Static Methods Finished ---

    // Private Static Methods:
    private static int calculateNetCalorie(Person person){
        return person.totalCaloriesTaken - person.totalCaloriesBurned - person.DAILY_CALORIE_NEEDS;
    }

    private static class Person {

        // Fields:
        private final String ID;
        private final String NAME;
        private final int AGE;
        private final int DAILY_CALORIE_NEEDS;

        private int totalCaloriesTaken;
        private int totalCaloriesBurned;
        // --- Fields Finished ---

        // Constructor:
        Person(String[] arr) {

            // Define Fields
            this.ID = arr[0];
            this.NAME = arr[1];
            this.totalCaloriesTaken = 0;
            this.totalCaloriesBurned = 0;
            this.AGE = 2022 - Integer.parseInt(arr[5]);

            // Define Local Variables
            String gender = arr[2];
            int weight = Integer.parseInt(arr[3]);
            int height = Integer.parseInt(arr[4]);

            // Calculate DAILY_CALORIE_NEEDS
            if (gender.equals("male"))
                this.DAILY_CALORIE_NEEDS = (int) Math.round(66 + 13.75*weight +5*height - 6.8* AGE);
            else
                this.DAILY_CALORIE_NEEDS = (int) Math.round( 665 + 9.6*weight + 1.7*height - 4.7* AGE);

            // Add Every Instance To A HashMap
            PERSON_HASH_MAP.put(this.ID, this);
        }
        // --- Constructor Finished ---
    }
}