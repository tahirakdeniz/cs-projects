import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class SportController {
    /*
    * Static methods for sport class
    */

    private final static HashMap<String, Sport> SPORT_HASH_MAP = new HashMap<>();

    public static void readSportFile(String filePath) {
        try {
            File file = new File(filePath);
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                String[] arr = fileScanner.nextLine().split("\t");
                new Sport(arr[0], arr[1], Integer.parseInt(arr[2]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static String getSportNameById(String id) {
        return getSportInstanceById(id).getNAME();
    }

    public static int getSportCalorieById(String id) {
        return getSportInstanceById(id).getCALORIE();
    }

    private static Sport getSportInstanceById(String id) {
        return SPORT_HASH_MAP.get(id);
    }

    private static class Sport extends CalorieChanger {

        Sport(String id, String name, int calorie) {
            super(id, name, calorie);
            SPORT_HASH_MAP.put(id, this);
        }
    }
}
