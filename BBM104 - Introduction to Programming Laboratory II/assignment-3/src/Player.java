public class Player implements Comparable<Player> {
    private String playerName;
    private Integer score;

    public Player(String playerName, int Score) {
        this.playerName = playerName;
        score = Score;
    }

    @Override
    public int compareTo(Player o) {
        return score.compareTo(o.score);
    }

    public String getPlayerName() {
        return playerName;
    }

    public Integer getScore() {
        return score;
    }

    @Override
    public String toString() {
        return playerName + " " + score;

    }
}
