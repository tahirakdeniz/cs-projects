import java.util.*;

/**
 * A subclass of FileCreator.
 * Stores grid that is read from file (filePath is given in Constructor).
 */
public class Grid extends FileCreator{

    /**
     * List of Lists of JewelTypes. List keep first column, then rows.
     * Ex: 0th item of gridColumnsList means 0th column (a list). (the leftmost one)
     * 0th item of a column means 0th row. (the topmost one)
     */
    private List<List<JewelType>> gridColumnsList = new ArrayList<>();

    /**
     * Constructor to initialize a Grid object. Reads file and stores grid.
     * @param filePath file path of file that stores grid.
     * @param jewelTypeMap a JewelTypeMap object that stores JewelType 's in a Map. It is used to get JewelType for given symbol String.
     */
    public Grid(String filePath, JewelTypeMap jewelTypeMap) {
        super(filePath);
        while (getScanner().hasNextLine()) {

            String[] symbols = getScanner().nextLine().split(" ");

            for (int i = 0 ; i < symbols.length; i++) {

                // Get JewelType :
                String symbol = symbols[i];
                JewelType jewelType = jewelTypeMap.getJewelType(symbol);
                // ===

                // If gridColumnList don't column in ith index create it:
                List<JewelType> column;
                try {
                    column = gridColumnsList.get(i);
                }
                catch (IndexOutOfBoundsException e) {
                    gridColumnsList.add(new ArrayList<>());
                    column = gridColumnsList.get(i);
                }
                // ===

                column.add(jewelType);
            }
        }
    }

    /**
     * Get JewelType by index, and checks that whether index exists.
     * @param index index of desired JewelType
     * @return JewelType
     * @throws IndexOutOfGridException when indexes aren't in the grid or JewelType 's are null.
     */
    public JewelType getJewelType(Index index) throws IndexOutOfGridException {
        JewelType jewelType;

        try {
            List<JewelType> columnList = gridColumnsList.get(index.getColumn());
            jewelType = columnList.get(index.getRow());

        } catch (Exception e) {
            throw new IndexOutOfGridException();
        }

        if (jewelType == null)
            throw new IndexOutOfGridException();

        return jewelType;
    }

    /**
     * Deletes given Index 'es in grid, and fill empty with null JewelTypes. Size of grid doesn't change.
     * @param indexCollection indexes to delete
     */
    public void deleteIndexes(Collection<Index> indexCollection) {
        for (Index index :
                indexCollection) {
            List<JewelType> columnList = gridColumnsList.get(index.getColumn());
            columnList.remove(index.getRow());
            columnList.add(0, null);
        }

    }

    public int calculateScore(Collection<Index> indexCollection) {
        int score = 0;

        for (Index index :
                indexCollection) {
            try {
                JewelType jewelType = getJewelType(index);
                score += jewelType.getScore();
            } catch (IndexOutOfGridException e) {
                e.printStackTrace();
            }
        }

        return score;
    }

    /**
     * Creates a string that is view of the read grid. Items that are in the same row showed as side by side contrary to the store style of the grid.
     * @return a string to print items of grid.
     */
    public String toString() {

        // Calculate rowSize and colSize (column size):
        int rowSize = gridColumnsList.get(0).size();
        int colSize = gridColumnsList.size();

        StringBuilder stringBuilder = new StringBuilder();

        for (int row = 0;  row < rowSize; row++) {
            for (int column = 0; column < colSize; column++){

                JewelType jewelType;

                try {
                    jewelType = getJewelType(new Index(row, column));
                    stringBuilder.append(jewelType.getSymbol()).append(" ");
                }
                catch (IndexOutOfGridException e) {
                    stringBuilder.append("  ");
                }

            }
            stringBuilder.append("\n");
        }

        return stringBuilder.toString();
    }

}
