import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Command {

    // Fields
    private Scanner inputScanner;
    private FileWriter outputFileWriter;

    // Constructor
    Command(String inputFilePath, String outputFilePath) {
        try {

            // Create input scanner
            File inputCommandsFile = new File(inputFilePath);
            this.inputScanner = new Scanner(inputCommandsFile);

            // Create output file
            File outputFile = new File(outputFilePath);
            outputFile.createNewFile();

            // Create output file writer
            this.outputFileWriter = new FileWriter(outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Methods
    public void runCommands() {
        try {

            final ArrayList<String> personIdArrayList = new ArrayList<>();

            while (inputScanner.hasNextLine()) {

                String[] commandArr = inputScanner.nextLine().split("\t");

                if (commandArr.length == 3) {

                    final String PERSON_ID = commandArr[0];
                    final String CALORIE_CHANGER_ID = commandArr[1];
                    final int CALORIE_CHANGER_COUNT = Integer.parseInt(commandArr[2]);

                    if (!personIdArrayList.contains(PERSON_ID)) personIdArrayList.add(PERSON_ID);

                    String outputString;
                    if (commandArr[1].charAt(0) == '2') {
                        outputString = sportDone(PERSON_ID, CALORIE_CHANGER_ID, CALORIE_CHANGER_COUNT);
                    } else {
                        outputString = foodDone(PERSON_ID, CALORIE_CHANGER_ID, CALORIE_CHANGER_COUNT);
                    }
                    outputFileWriter.write(outputString);

                } else {

                    if (commandArr[0].equals("printWarn")) {
                        //PrintWarn Command
                        boolean isWarned = false;
                        for (String personId: personIdArrayList)
                            if (PersonController.isWarn(personId)) {
                                isWarned = true;
                                outputFileWriter.write(PersonController.outputStringById(personId));
                            }
                        if (!isWarned)
                            outputFileWriter.write("there\tis\tno\tsuch\tperson\n");

                    } else if (commandArr[0].equals("printList")) {
                        //PrintList Command
                        for (String personId: personIdArrayList)
                            outputFileWriter.write(PersonController.outputStringById(personId));

                    } else {
                        // Print(<ID>) Command
                        String personId = commandArr[0].substring(6, 11);
                        outputFileWriter.write(PersonController.outputStringById(personId));
                    }
                }
                if (inputScanner.hasNextLine()) outputFileWriter.write("***************\n");
                outputFileWriter.flush();
            }
            outputFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    // Helper
    private String sportDone(String personId, String sportId, int sportDuration) {
        int caloriesBurned = Math.round((float) SportController.getSportCalorieById(sportId)* sportDuration / 60);
        PersonController.increaseCaloriesBurned(personId, caloriesBurned);
        return personId + "\thas\tburned\t" + caloriesBurned + "kcal\tthanks\tto\t" + SportController.getSportNameById(sportId)+ "\n";
    }

    private String foodDone(String personId, String foodId, int foodPortion) {
        int caloriesTaken = Math.round((float) FoodController.getFoodCalorieById(foodId) * foodPortion);
        PersonController.increaseCaloriesTaken(personId, caloriesTaken);
        return personId + "\thas\ttaken\t" + caloriesTaken + "kcal\tfrom\t" + FoodController.getFoodNameById(foodId)+ "\n";
    }

}

