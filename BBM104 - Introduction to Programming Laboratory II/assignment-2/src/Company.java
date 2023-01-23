public class Company extends Property{

    public Company(String[] arr) {
        super(arr);
    }

    @Override
    public String run(Player player) {
        setRent(4 * player.getDice());
        return super.run(player);
    }
}
