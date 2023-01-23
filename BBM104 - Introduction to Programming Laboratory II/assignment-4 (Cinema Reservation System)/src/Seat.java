public class Seat {

    private int row;
    private int column;
    private User owner;

    public Seat(int row, int column, User owner) {
        this.owner = owner;
        this.row = row;
        this.column = column;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
