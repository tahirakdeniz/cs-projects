public class FreeParking extends Square{
    protected FreeParking(int location) {
        super(location);
    }

    @Override
    public String run(Player player) {
        player.setGameNumberToWait(1);
        return "went to free parking";
    }
}
