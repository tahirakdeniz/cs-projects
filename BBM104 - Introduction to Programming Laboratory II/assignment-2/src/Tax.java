public class Tax extends Square{
    protected Tax(int location) {
        super(location);
    }

    @Override
    public String run(Player player) {
        Banker banker = Banker.getInstance();
        player.payTo(100, banker);
        return "paid Tax";
    }


}
