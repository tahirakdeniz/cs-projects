public class Go extends Square {

    protected Go(int location) {
        super(location);
    }

    @Override
    public String run(Player player) {
        Banker banker = Banker.getInstance();
        banker.payTo(200, player);
        return "is in Go square";
    }
}
