class WildcardJewelType extends JewelType {

    WildcardJewelType(String symbol, int score, Index... directionIndexes) {
        super(symbol, score, directionIndexes);
    }

    @Override
    public boolean isMatchingWith(JewelType jewelType) {
        return !(jewelType instanceof MathematicalJewelType);
    }
}