public class ClassicalJewelType extends JewelType {

    public ClassicalJewelType(int score, String symbol, Index... directionIndexes) {
        super(symbol, score, directionIndexes);
    }

    @Override
    public boolean isMatchingWith(JewelType jewelType) {
        return this == jewelType;
    }
}
