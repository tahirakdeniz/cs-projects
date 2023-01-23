/*
* Class To Keep Squares In An Array
* This class contains just static methods and variables (Utility Class)
*
* Note: Array indexes start from 0, but square locations start from 1,
* so the program subtracts 1 from given location.
* */


public class SquareCollection {

    private static final Square[] SQUARE_ARR;
    public static final int SQUARE_NUMBER;

    // INITIALIZE STATIC VARIABLES
    static {

        // Initialize:
        final String PROPERTY_FILE_NAME = "property.json";
        SQUARE_NUMBER = 40;
        SQUARE_ARR = new Square[SQUARE_NUMBER];
        PropertyJsonReader propertyJsonReader = new PropertyJsonReader(PROPERTY_FILE_NAME);
        // ---

        // Insert Properties To Collection
        for (String[] arr : propertyJsonReader.getLAND_DATA_LIST())
            insertSquare(new Land(arr));

        for (String[] arr : propertyJsonReader.getRAILROAD_DATA_LIST())
            insertSquare(new Railroads(arr));

        for (String[] arr : propertyJsonReader.getCOMPANY_DATA_LIST())
            insertSquare(new Company(arr));
        // ---

        // Insert Other Squares To Collection:
        insertSquare(
                new Go(1),
                new CommunityChest(3),
                new Tax(5),
                new Chance(8),
                new Jail(11),
                new CommunityChest(18),
                new FreeParking(21),
                new Chance(23),
                new GoToJail(31),
                new CommunityChest(34),
                new Chance(37),
                new Tax(39)
        );
        // ---
    }

    // Methods:
    public static Square squareByLocation(int location){
        return SQUARE_ARR[location - 1];
    }
    // ---

    // Helpers:
    private static void insertSquare(Square... squares) {
        for (Square square : squares)
            SQUARE_ARR[square.getLOCATION() - 1] = square;
    }
    // ---
}
