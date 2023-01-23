public abstract class Property extends Square {

    // TODO Acceptable Comment Parts.

    // Fields:
    private final String NAME;
    private final int COST;
    private int rent;
    private Player owner;
    // ---

    // Constructor:
    protected Property(String[] arr) {
        super(Integer.parseInt(arr[0]));
        NAME = arr[1];
        COST = Integer.parseInt(arr[2]);
    }
    // ---

    // Accessor:
    public int getCOST() {
        return COST;
    }

    public String getNAME() {
        return NAME;
    }
    // ---

    // Mutators:
    public void setRent(int rent) {
        this.rent = rent;
    }
    // ---

    // Methods:
    @Override
    public String run(Player player) {
        /* Method to buy or pay rent
        * Checks owner
        * if player cannot pay the returns null String, otherwise returns what happened and name. */

        Banker banker = Banker.getInstance();
        String output = "";

        if (owner == null) {

            // TRY TO BUY:
            if (player.payTo(COST, banker)) {
                player.addNewProperty(this);
                owner = player;

                if (this instanceof Railroads)
                    player.setRailRoadCount(player.getRailRoadCount() + 1);

                output = "bought " + NAME;
            }
        }
        else if (owner != player) {

            // TRY TO PAY RENT:
            if (player.payTo(rent, owner))
                output = "paid rent for " + NAME;

        }
        else output = "has " + NAME;

        return output;
    }
    // ---
}
