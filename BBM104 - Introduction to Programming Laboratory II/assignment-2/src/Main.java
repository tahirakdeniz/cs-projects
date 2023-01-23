import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main {

    // Define Constants:
    private final static Banker BANKER = Banker.getInstance();
    private final static Player PLAYER1 = Player.playerById(1);
    private final static Player PLAYER2 = Player.playerById(2);
    // ---

    public static void main(String[] args) {

        /* Reads command file line by line,
        * if line is show command, run shows command.
        * else (dice command) get dice and player who plays.
        * if it isn't waiting, change their location.
        * And run the Square where player is in. */

        // Constants:
        final String COMMAND_FILE_PATH = args[0];
        final String OUTPUT_FILE_PATH = "monitoring.txt";
        // ---

        try {

            // Define variables to read and writer:
            Scanner commandScanner = new Scanner(new File(COMMAND_FILE_PATH));
            File outputFile = new File(OUTPUT_FILE_PATH);
            outputFile.createNewFile();
            FileWriter outputFileWriter = new FileWriter(outputFile);
            String output;
            // ---

            boolean isGameContinuing = true;

            // Loop to read line by line:
            while (commandScanner.hasNextLine() & isGameContinuing) {

                String commandLine = commandScanner.nextLine();

                if (commandLine.equals("show()"))
                    output = showCommand();

                else {
                    // DICE COMMAND:
                    String processingOutput;

                    // Take player and dice:
                    String[] diceCommand = commandLine.split(" ")[1].split(";");
                    int playerId = Integer.parseInt(diceCommand[0]);
                    int dice = Integer.parseInt(diceCommand[1]);

                    Player player = Player.playerById(playerId);
                    player.setDice(dice);
                    // ---


                    if (player.getGameNumberToWait() > 0) {
                        // == Player is Waiting ==
                        Square square = SquareCollection.squareByLocation(player.getLocation());

                        if (square instanceof Jail)
                            processingOutput = "in jail";
                        else
                            processingOutput = "in free parking";

                        player.setWaitedGame(player.getWaitedGame() + 1);
                        player.setGameNumberToWait(player.getGameNumberToWait() - 1);
                        processingOutput += " (count=" + player.getWaitedGame() + ")";
                    }

                    else {

                        // Reset waitedCount when waiting finish
                        if (player.getWaitedGame() != 0)
                            player.setWaitedGame(0);

                        // == Change player location and run the square ==
                        player.advanceTo(player.getLocation() + dice);
                        processingOutput = player.runSquare();

                    }

                    /* if one of the player goes Bankrupt, write to output
                    * and doesn't run loop again. */

                    if (PLAYER1.isBankrupted()) {
                        isGameContinuing = false;
                        processingOutput += " | " + PLAYER1.getNAME() + " goes Bankrupt";
                    }

                    else if (PLAYER2.isBankrupted()) {
                        isGameContinuing = false;
                        processingOutput += " | " + PLAYER2.getNAME() + " goes Bankrupt";
                    }

                    output =
                            player.getNAME() + "\t" +
                            player.getDice() + "\t" +
                            player.getLocation() + "\t" +
                            PLAYER1.getMoney() + "\t" +
                            PLAYER2.getMoney() + "\t" +
                            player.getNAME() + " " +
                            processingOutput + "\n";

                }

                outputFileWriter.write(output);
                outputFileWriter.flush();
            }
            // ---

            outputFileWriter.write(showCommand());
            outputFileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        // ---
    }

    // Helper Methods:
    private static String showCommand() {

        /* Helper method for Main Class
        * To show money, properties, etc. of players and bankers.
        * player who have more money will be winner.
        * if player has same money, there isn't winner. */

        String winner;

        if (PLAYER1.getMoney() > PLAYER2.getMoney())
            winner = "Winner " + PLAYER1.getNAME() + "\n";

        else if (PLAYER2.getMoney() > PLAYER1.getMoney())
            winner = "Winner " + PLAYER2.getNAME() + "\n";

        else
            winner = "Their money is equal, so there isn't a winner \n";

        String output = "-------------------------------------------------------------------------------------------------------------------------\n" +
                PLAYER1.getNAME() + "\t" + PLAYER1.getMoney() + "\thave: " + PLAYER1.ownedProperties() + "\n" +
                PLAYER2.getNAME() + "\t" + PLAYER2.getMoney() + "\thave: " + PLAYER2.ownedProperties() + "\n" +
                BANKER.getNAME() + "\t" +BANKER.getMoney() + "\n" +
                winner +
                "-------------------------------------------------------------------------------------------------------------------------\n";

        return output;
    }
    // ---
}

