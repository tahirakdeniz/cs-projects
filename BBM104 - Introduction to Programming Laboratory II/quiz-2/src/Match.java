public class Match {

    private String sportTypeKey;
    private String firstTeamName;
    private String secondTeamName;
    private int firstTeamScore;
    private int secondTeamScore;

    Match(String inputLine) {
        String[] matchArr = inputLine.split("\t");
        String[] matchScore = matchArr[3].split(":");

        this.sportTypeKey = matchArr[0];
        this.firstTeamName = matchArr[1];
        this.secondTeamName = matchArr[2];
        this.firstTeamScore = Integer.parseInt(matchScore[0]);
        this.secondTeamScore = Integer.parseInt(matchScore[1]);
    }

    public String getSportTypeKey() {
        return sportTypeKey;
    }

    public String getFirstTeamName() {
        return firstTeamName;
    }

    public String getSecondTeamName() {
        return secondTeamName;
    }

    public int getFirstTeamScore() {
        return firstTeamScore;
    }

    public int getSecondTeamScore() {
        return secondTeamScore;
    }
}
