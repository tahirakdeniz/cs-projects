public class Jail extends Square {

    protected Jail(int location) {
        super(location);
    }

    @Override
    public String run(Player player) {
        player.setGameNumberToWait(3);
        return "went to jail";
    }
}
