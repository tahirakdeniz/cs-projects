import java.util.ArrayList;

/*
* Class For Player 1 and Player 2
* There are just two instances, so I used private constructor (Singleton design pattern)
* */

public class Player extends GamePerson{

    // Instances:
    private static final Player[] PLAYERS = {
            new Player("Player 1"),
            new Player("Player 2")
    };
    // ---

    // Fields:
    private int location;
    private int dice;
    private int waitedGame = 0;
    private int gameNumberToWait = 0;
    private int railRoadCount = 0;
    private boolean bankrupted = false;
    private final ArrayList<Property> OWNED_PROPERTIES = new ArrayList<>();
    // ---

    // Constructor:
    private Player(String name) {
        super(name, 15000);
        location = 1;
    }
    // ---

    // Instance Accessor:
    public static Player playerById(int id) {
        /* Returns player depends on given id.
        * id == 1 for player 1, id == 2 for player 2 */
        return PLAYERS[id - 1];
    }
    // ---

    // Accessors:
    public int getLocation() { return location; }

    public int getDice() { return dice; }

    public int getWaitedGame() { return waitedGame; }

    public int getRailRoadCount() { return railRoadCount; }

    public boolean isBankrupted() { return bankrupted; }

    public int getGameNumberToWait() { return gameNumberToWait; }
    // ---

    // Mutators:
    public void setDice(int dice) { this.dice = dice; }

    public void setWaitedGame(int waitedGame) { this.waitedGame = waitedGame; }

    public void setLocation(int location) { this.location = location; }

    public void setRailRoadCount(int railRoadCount) { this.railRoadCount = railRoadCount; }

    public void setGameNumberToWait(int gameNumberToWait) { this.gameNumberToWait = gameNumberToWait; }

    // ---

    // Methods:
    public void advanceTo(int location) {
        /* Method to advance to a square using given location.
        * if the location bigger than square number make it small (for rounding map)
        * Also, if the Square passes by go by completing the map, run go Square for this player. */

        Square goSquare = SquareCollection.squareByLocation(1);
        int newLocation = location;

        if (newLocation > SquareCollection.SQUARE_NUMBER) {

            newLocation -= SquareCollection.SQUARE_NUMBER;

            // Note: if new location is 1, go square runs already. So, to prevent double running
            if (newLocation != 1)
                goSquare.run(this);

        }
        else if (newLocation < 1)
            newLocation += SquareCollection.SQUARE_NUMBER;

        this.location = newLocation;
    }


    public String runSquare() {
        // Run square that where player is and return output to write /--
        Square square = SquareCollection.squareByLocation(location);
        return square.run(this);
    }

    public boolean payTo(int money, GamePerson gamePerson) {

        /* payTo Method
        * description is in the super class. */

        boolean isPaid = getMoney() >= money;

        if (isPaid) {
            setMoney(getMoney() - money);
            gamePerson.setMoney(gamePerson.getMoney() + money);
        }
        if (getMoney() == 0)
            bankrupted = true;
        else
            bankrupted = !isPaid;
        return isPaid;
    }

    public Player otherPlayer() {

        // == Returns other player. == //
        if (this == PLAYERS[0])
            return PLAYERS[1];
        else if (this == PLAYERS[1])
            return PLAYERS[0];
        else
            return null;
    }

    public void addNewProperty(Property property) {
        OWNED_PROPERTIES.add(property);
    }

    public String ownedProperties(){

        /* Returns items of OWNED_PROPERTIES list
        * by turning them to needed string format to write output*/

        StringBuilder output = new StringBuilder();

        for (Property property :
                OWNED_PROPERTIES) {
            output.append(" ,").append(property.getNAME());
        }
        output.delete(0, 2);
        return  output.toString();
    }
    // ---
}
