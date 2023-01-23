public class Hall {
    private final String name;
    private final double pricePerSeat;
    private final int rowNumber;
    private final int columnNumber;

    public Hall(String name, double pricePerSeat, int rowNumber, int columnNumber) {
        this.name = name;
        this.pricePerSeat = pricePerSeat;
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
    }

    public String getName() {
        return name;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    @Override
    public String toString() {
        return name;
    }
}
