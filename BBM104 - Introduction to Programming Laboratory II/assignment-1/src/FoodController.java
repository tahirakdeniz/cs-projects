import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class FoodController {
    private final static HashMap<String, Food> FOOD_HASH_MAP = new HashMap<>();

    public static void readFoodFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] arr = fileScanner.nextLine().split("\t");
                new Food(arr[0], arr[1], Integer.parseInt(arr[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getFoodNameById(String id) {
        return getFoodInstanceById(id).getNAME();
    }

    public static int getFoodCalorieById(String id) {
        return getFoodInstanceById(id).getCALORIE();
    }

    private static Food getFoodInstanceById(String id) {
        return FOOD_HASH_MAP.get(id);
    }

    private static class Food extends CalorieChanger {

        Food(String id, String name, int calorie) {
            super(id, name, calorie);
            FOOD_HASH_MAP.put(id, this);
        }
    }
}
