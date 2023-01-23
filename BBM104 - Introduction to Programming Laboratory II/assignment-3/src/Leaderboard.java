import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard extends FileCreator{

    private List<Player> playerList = new ArrayList<>();
    private Player gamePlayer;

    public Leaderboard(String filePath) {
        super(filePath);

        // Add players to playerList
        while (getScanner().hasNextLine()) {
            String[] arr = getScanner().nextLine().split(" ");

            String playerName = arr[0];
            int score = Integer.parseInt(arr[1]);

            Player player = new Player(playerName, score);
            playerList.add(player);

        }
    }

    public String getRankString(String playerName, int score) {
        gamePlayer = new Player(playerName, score);
        this.playerList.add(gamePlayer);

        List<Player> playerListToSort = new ArrayList<>(this.playerList);
        Collections.sort(playerListToSort);

        int playerCount = playerListToSort.size();
        int rank = playerCount - Collections.binarySearch(playerListToSort, gamePlayer);

        int higherScoreIndex = playerCount - rank + 1;
        int lowerScoreIndex = playerCount - rank - 1;

        String output = "Your rank is " + rank + "/" + playerCount + ". ";

        try {
            Player higherScorePlayer = playerListToSort.get(higherScoreIndex);
            output += "Your score is " + (higherScorePlayer.getScore() - gamePlayer.getScore()) + " points lower than " + higherScorePlayer.getPlayerName() + ". ";
        } catch (Exception ignored) {}

        try {
            Player lowerScorePlayer = playerListToSort.get(lowerScoreIndex);
            output += "Your score is " + (- lowerScorePlayer.getScore() + gamePlayer.getScore()) + " points higher than " + lowerScorePlayer.getPlayerName() + ". ";
        }
        catch (Exception ignored) {}

        return output;
    }

    public void writeOutput() {
        try {
            for (Player player : playerList)
                getFileWriter().write(player.toString() + "\n");
            getFileWriter().close();
        } catch (IOException e) {
                e.printStackTrace();
        }
    }

}
