public class GoToJail extends Square {

    protected GoToJail(int location) {
        super(location);
    }

    @Override
    public String run(Player player) {
        player.setLocation(11);
        return player.runSquare();
    }
}
