import java.util.*;

/**
 * A class to run game, and write obtained output.
 */
public class Game {

    private Grid grid;
    private Commands commands;
    private Leaderboard leaderboard;
    private Output output;

    private StringBuilder outputStringBuilder;

    public Game(Grid grid, Commands commands, Leaderboard leaderboard, Output output) {
        this.grid = grid;
        this.commands = commands;
        this.leaderboard = leaderboard;
        this.output = output;

        outputStringBuilder = new StringBuilder("Game grid:\n\n");
        outputStringBuilder.append(grid).append("\n");
    }

    public void run() {

        // Get given indexes as Iterator from commands:
        Iterator<Index> indexIterator = commands.getIndexIterator();

        int totalScore = 0;

        // Searches Index 'es to delete, deletes them, calculates the score.
        while (indexIterator.hasNext()) {

            Index givenIndex = indexIterator.next();

            outputStringBuilder.append("Select coordinate or enter E to end the game: ").append(givenIndex).append("\n\n");

            /* Check the givenIndex:
            * If givenIndex isn't in grid, loop continues to new index.
            * Changes output.
            * */
            JewelType givenJewelType;

            try {
                givenJewelType = grid.getJewelType(givenIndex);
            }
            catch (IndexOutOfGridException e) {
                outputStringBuilder.append("Please enter a valid coordinate\n\n");
                continue;
            }
            // ===

            // Get direction Index 'es as an Iterator from givenJewelType:
            Iterator<Index> directionIndexIterator = givenJewelType.getDirectionIndexIterator();

            int score = 0;

            // Find if are there any exist matched jewel in the grid:
            while (directionIndexIterator.hasNext()) {

                Index directionIndex = directionIndexIterator.next();

                Index thisIndex;
                Index nextIndex = givenIndex;

                JewelType thisJewelType;
                JewelType nextJewelType;

                boolean isBroke = false;
                Set<Index> indexSetToDelete = new LinkedHashSet<>();

                for (int i = 0; i < 2; i++) {

                    // Create Index 'es:
                    thisIndex = nextIndex;
                    nextIndex = thisIndex.add(directionIndex);
                    // ===

                    /*
                    * If index doesn't exist in grid , break the loop to search in new direction.
                    * Output doesn't changes.
                    * */
                    try {
                        thisJewelType = grid.getJewelType(thisIndex);
                        nextJewelType = grid.getJewelType(nextIndex);
                    }
                    catch (IndexOutOfGridException e) {
                        isBroke = true;
                        break;
                    }

                    // Check Matching:
                    /*
                    * If there exist an index that doesn't exist, break the loop to search in new direction.
                    * Output doesn't changes.
                    * */
                    if (thisJewelType.isMatchingWith(nextJewelType)) {
                        indexSetToDelete.add(thisIndex);
                        indexSetToDelete.add(nextIndex);
                    }
                    else {
                        isBroke = true;
                        break;
                    }
                    // ===
                }

                /*
                * If loop above isn't broken that means program found enough jewels in grid.
                * Now delete them and calculate score.
                * */
                if (!isBroke) {
                    score = grid.calculateScore(indexSetToDelete);
                    grid.deleteIndexes(indexSetToDelete);
                    break;
                }

            }

            totalScore += score;
            outputStringBuilder
                    .append(grid).append("\n")
                    .append("Score: ").append(score).append(" points").append("\n\n");

        }

        outputStringBuilder
                .append("Select coordinate or enter E to end the game: E\n\n")
                .append("Total score: ").append(totalScore).append(" points").append("\n\n")
                .append("Enter name: ").append(commands.getPlayerName()).append("\n\n")
                .append(leaderboard.getRankString(commands.getPlayerName(), totalScore)).append("\n\n")
                .append("Good bye!");

        leaderboard.writeOutput();
    }

    public void writeOutput(){
        output.write(outputStringBuilder.toString());
    }
}
