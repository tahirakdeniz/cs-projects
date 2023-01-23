/* Class For Banker
* Used singleton design pattern */

public class Banker extends GamePerson {
    private static final Banker banker = new Banker("Banker");

    private Banker(String name) {
        super(name, 100000);
    }

    public static Banker getInstance() {
        return banker;
    }

    @Override
    public boolean payTo(int money, GamePerson gamePerson) {

        /* it returns true every time contrary to the player
        * because assumes banker can pay everytime even their money become less than zero.
        * look method in the super class for description */

        setMoney(getMoney() - money);
        gamePerson.setMoney(gamePerson.getMoney() + money);
        return true;
    }
}
