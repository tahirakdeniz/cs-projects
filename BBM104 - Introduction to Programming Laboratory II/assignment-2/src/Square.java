/* abstract class to control squares
* this class aims implementing polymorphism
* run method:
* runs what square does to the player. */

public abstract class Square {
    private final int LOCATION;

    protected Square(int location) {
        LOCATION = location;
    }

    public int getLOCATION() {
        return LOCATION;
    }

    abstract public String run(Player player);
}
