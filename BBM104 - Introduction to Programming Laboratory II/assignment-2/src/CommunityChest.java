public class CommunityChest extends Square {

    protected CommunityChest(int location) {
        super(location);
    }

    @Override
    public String run(Player player) {
        Banker banker = Banker.getInstance();
        String card = CardCollection.nextCommunityChestCard();
        Player otherPlayer = player.otherPlayer();

        String output = "";

        switch (card) {
            case "Advance to Go (Collect $200)":
                player.setLocation(1);
                output = player.runSquare();
                break;
            case "Bank error in your favor - collect $75":
                banker.payTo(75, player);
                break;
            case "Doctor's fees - Pay $50":
            case "Pay School Fees of $50":
                player.payTo(50, banker);
                break;
            case "It is your birthday Collect $10 from each player":
                otherPlayer.payTo(10, player);
                break;
            case "Grand Opera Night - collect $50 from every player for opening night seats":
                otherPlayer.payTo(50, player);
                break;
            case "Income Tax refund - collect $20":
                banker.payTo(20, player);
                break;
            case "Life Insurance Matures - collect $100":
            case "You inherit $100":
                banker.payTo(100, player);
                break;
            case "Pay Hospital Fees of $100":
                player.payTo(100, banker);
                break;
            case "From sale of stock you get $50":
                banker.payTo(50, player);
                break;
            default:
                break;
        }
        return "draw Community Chest Card: " + card + " | " + output;
    }
}
