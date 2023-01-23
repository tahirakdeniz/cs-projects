/* Abstract class for bankers and players
* Both them have money, name fields, and payTo method. */

public abstract class GamePerson {
    private final String NAME;
    private int money;

    protected GamePerson(String name, int money) {
        NAME = name;
        this.money = money;
    }

    public String getNAME() {
        return NAME;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    /* == payTo Method ==
    * it returns true when this can pay that money, and pays. Otherwise, returns false.
    * if player (it is for just player instances) cannot pay money or after money it hasn't any money, it goes bankrupt. */
    abstract public boolean payTo(int money, GamePerson gamePerson);
}
