import java.util.*;

public abstract class JewelType {

    private int score;
    private String symbol;
    private Iterator<Index> directionIndexIterator;

    JewelType(String symbol, int score, Index... directionIndexes) {
        this.symbol = symbol;
        this.score = score;
        directionIndexIterator = Arrays.stream(directionIndexes).iterator();
    }

    public abstract boolean isMatchingWith(JewelType jewelType);

    public Iterator<Index> getDirectionIndexIterator() {
        return directionIndexIterator;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getScore() {
        return score;
    }
}







