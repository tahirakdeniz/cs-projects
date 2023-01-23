public class Chance extends Square {

    protected Chance(int location) {
        super(location);
    }

    @Override
    public String run(Player player) {
        Banker banker = Banker.getInstance();
        String card = CardCollection.nextChanceCard();

        String output = null;

        switch (card) {
            case "Advance to Go (Collect $200)":
                player.setLocation(1);
                output = player.runSquare();
                break;
            case "Advance to Leicester Square":
                player.advanceTo(27);
                output = player.runSquare();
                break;
            case "Go back 3 spaces":
                player.advanceTo(player.getLocation() - 3);
                output = player.runSquare();
                break;
            case "Pay poor tax of $15":
                player.payTo(15, banker);
                break;
            case "Your building loan matures - collect $150":
                banker.payTo(150, player);
                break;
            case "You have won a crossword competition - collect $100":
                banker.payTo(100, player);
                break;
        }

        return "draw Chance Card: " + card + " | "+ output;
    }
}
