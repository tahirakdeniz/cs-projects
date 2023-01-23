public class MathematicalJewelType extends JewelType {


    MathematicalJewelType(String symbol, Index... directionIndexes) {
        super(symbol, 20, directionIndexes);
    }

    @Override
    public boolean isMatchingWith(JewelType jewelType) {
        return (jewelType instanceof MathematicalJewelType);
    }
}