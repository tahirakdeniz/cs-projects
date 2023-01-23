/**
 * Class for indexes to store column and row variables in one object.
 */
public class Index {
    private int column;
    private int row;

    public Index(int row, int column) {
        this.column = column;
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    /**
     * Calculate new Index that's column is sum of other two columns, and that's row is sum of other two rows.
     * @param index Index object to add
     * @return calculated new Index
     */
    public Index add(Index index){
        return new Index(index.row + this.row, index.column + this.column);
    }

    @Override
    public String toString() {
        return row + " " + column;
    }
}
