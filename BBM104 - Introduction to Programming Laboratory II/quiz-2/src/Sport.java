import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Hashtable;

public class Sport {

    // Instances
    static private Sport football = new Sport("Football.txt");
    static private Sport handball = new Sport("Handball.txt");
    static private Sport volleyball = new Sport("Volleyball.txt");
    static private Sport basketball = new Sport("Basketball.txt");


    private Hashtable<String, Team> teamHashtable = new Hashtable<>(4);
    private String outputFilePath;

    // Private Inner Class
    private class Team {

        // Fields
        private String nameOfClub;
        private int playedMatch;
        private int wonMatch;
        private int tieMatch;
        private int looseMatch;
        private int goalScored;
        private int goalConceded;
        private int totalPoints;

        // Constructor
        private Team (String nameOfClub) {
            this.nameOfClub = nameOfClub;
            teamHashtable.put(nameOfClub, this);
        }

         // Methods
         void play(int goalScored, int goalConceded) {
            this.playedMatch += 1;
            this.goalScored += goalScored;
            this.goalConceded += goalConceded;
         }

         void lost(int point) {
            this.totalPoints += point;
            this.looseMatch += 1;
         }

         void won(int point) {
            this.totalPoints += point;
            this.wonMatch += 1;
         }

         void tied(int point) {
            this.totalPoints += point;
            this.tieMatch += 1;
         }

         String out() {
             return nameOfClub + "\t" + playedMatch + "\t" + wonMatch + "\t" + tieMatch + "\t" + looseMatch + "\t" + goalScored+":"+goalConceded + "\t" + totalPoints;
         }

         boolean isBetterThan(Team team) {
            /** higher priority **/
            if (this.totalPoints < team.totalPoints) {
                return false;
            } else if (this.totalPoints > team.totalPoints) {
                return true;
            }
            else {
                if (this.goalScored - this.goalConceded > team.goalScored - team.goalConceded) {
                    return true;
                }
                else if (this.goalScored - this.goalConceded < team.goalScored - team.goalConceded) {
                    return false;
                }
                else {
                    if (this.nameOfClub.compareTo(team.nameOfClub) <= 0) {
                        return true;
                    }
                    else return false;
                }
            }
         }

    }


    // Private Constructor
    private Sport(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }


    // === METHODS ===

    // PRIVATE METHODS
    private Team[] orderedTeam() {

        Team[] teams = teamHashtable.values().toArray(new Team[4]);

        int lastIndex = teams.length - 1;
        while (lastIndex > 0) {
            for (int i = 0; i < lastIndex; i++) {
                if (teams[i+1].isBetterThan(teams[i])) {
                    // Swap
                    Team temp = teams[i];
                    teams[i] = teams[i+1];
                    teams[i+1] = temp;
                }
            }
            lastIndex-=1;
        }

        return teams;
    }

    private Team getTeam(String nameOfClub) {
         Team team;
         if (teamHashtable.containsKey(nameOfClub)) team = teamHashtable.get(nameOfClub);
         else team = new Team(nameOfClub);
         return team;
    }

    private void writeTeamsToFile() {
        try {
            // OPEN FILE
            File file = new File(this.outputFilePath);
            file.createNewFile();
            FileWriter outputFileWriter = new FileWriter(file);

            // WRITE LINE BY LINE
            Team[] teams = this.orderedTeam();
            for (int i = 0; i < teams.length; i++) {
                outputFileWriter.write((i + 1) +".\t"+ teams[i].out() + "\n");
            }

            // CLOSE FILE
            outputFileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // STATIC METHODS
    public static void writeResults() {
        football.writeTeamsToFile();
        basketball.writeTeamsToFile();
        handball.writeTeamsToFile();
        volleyball.writeTeamsToFile();
    }

    public static void readMatch(Match match) {
         String sportTypeKey = match.getSportTypeKey();
         String firstTeamName = match.getFirstTeamName();
         String secondTeamName = match.getSecondTeamName();
         int firstTeamScore = match.getFirstTeamScore();
         int secondTeamScore = match.getSecondTeamScore();

        Team firstTeam;
        Team secondTeam;
        switch (sportTypeKey) {
            case "F":

                firstTeam = football.getTeam(firstTeamName);
                secondTeam = football.getTeam(secondTeamName);

                firstTeam.play(firstTeamScore, secondTeamScore);
                secondTeam.play(secondTeamScore, firstTeamScore);

                if (firstTeamScore > secondTeamScore) {
                    firstTeam.won(3);
                    secondTeam.lost(0);
                }
                else if (firstTeamScore < secondTeamScore) {
                    firstTeam.lost(0);
                    secondTeam.won(3);
                }
                else {
                    firstTeam.tied(1);
                    secondTeam.tied(1);
                }
                break;

            case "H":

                firstTeam = handball.getTeam(firstTeamName);
                secondTeam = handball.getTeam(secondTeamName);


                firstTeam.play(firstTeamScore, secondTeamScore);
                secondTeam.play(secondTeamScore, firstTeamScore);

                if (firstTeamScore > secondTeamScore) {
                    firstTeam.won(2);
                    secondTeam.lost(0);
                }
                else if (firstTeamScore < secondTeamScore) {
                    firstTeam.lost(0);
                    secondTeam.won(2);
                }
                else {
                    firstTeam.tied(1);
                    secondTeam.tied(1);
                }

                break;

            case "V":
                firstTeam = volleyball.getTeam(firstTeamName);
                secondTeam = volleyball.getTeam(secondTeamName);

                firstTeam.play(firstTeamScore, secondTeamScore);
                secondTeam.play(secondTeamScore, firstTeamScore);


                if (firstTeamScore == 3) {

                    if (secondTeamScore == 2) {
                        firstTeam.won(2);
                        secondTeam.lost(1);

                    }
                    else {
                        firstTeam.won(3);
                        secondTeam.lost(0);
                    }
                }
                else if (secondTeamScore == 3) {

                    if (firstTeamScore == 2) {
                        secondTeam.won(2);
                        firstTeam.lost(1);

                    }
                    else {
                        secondTeam.won(3);
                        firstTeam.lost(0);
                    }
                }

                break;

            case "B":
                firstTeam = basketball.getTeam(firstTeamName);
                secondTeam = basketball.getTeam(secondTeamName);

                firstTeam.play(firstTeamScore, secondTeamScore);
                secondTeam.play(secondTeamScore, firstTeamScore);

                if (firstTeamScore > secondTeamScore) {
                    firstTeam.won(2);
                    secondTeam.lost(1);
                }
                else {
                    firstTeam.lost(1);
                    secondTeam.won(2);
                }

                break;
            default:
                System.err.println(" -- Wrong Sport Type -- ");
                break;
        }
    }
}