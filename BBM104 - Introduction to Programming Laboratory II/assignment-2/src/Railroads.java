public class Railroads extends Property{

    public Railroads(String[] arr) {
        super(arr);
    }

    @Override
    public String run(Player player) {
        // Calculate rent using given dice (field of player):
        Player otherPlayer = player.otherPlayer();
        setRent(25*otherPlayer.getRailRoadCount());
        return super.run(player);
    }
}
