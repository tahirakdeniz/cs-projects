public class Main {
    public static void main(String[] args) {

        // Create direction Indexes:
        Index upward = new Index(-1, 0);
        Index downward = new Index(+1, 0);
        Index left = new Index(0, -1);
        Index right = new Index(0, +1);
        Index rightUpward = new Index(-1, +1);
        Index rightDownward = new Index(+1, +1);
        Index leftUpward = new Index(-1, -1);
        Index leftDownward = new Index(+1, -1);
        // ===

        // Create JewelTypes in:
        JewelType diamond = new ClassicalJewelType(30, "D", leftUpward, rightDownward, rightUpward, leftDownward);
        JewelType square = new ClassicalJewelType(15, "S", left, right);
        JewelType triangle = new ClassicalJewelType(15, "T", upward, downward);
        JewelType wildcard = new WildcardJewelType("W", 10, upward, downward, left, right, leftUpward, rightDownward, rightUpward,  leftDownward);
        JewelType minus = new MathematicalJewelType("-", left, right);
        JewelType plus = new MathematicalJewelType("+", left, right, upward, downward);
        JewelType bar = new MathematicalJewelType("|", upward, downward);
        JewelType slash = new MathematicalJewelType("/", rightUpward, leftDownward);
        JewelType backSlash = new MathematicalJewelType("\\", leftUpward, rightDownward );
        // ===


        // Create JewelTypeMap to store created JewelType 's:
        JewelTypeMap jewelTypeMap = new JewelTypeMap(diamond, square, triangle, wildcard, minus, plus, bar, slash, backSlash);

        // Create file paths:
        final String GRID_FILE_PATH = args[0];
        final String COMMANDS_FILE_PATH = args[1];
        final String OUTPUT_FILE_PATH = "monitoring.txt";
        final String LEADERBOARD_FILE_PATH = "leaderboard.txt";
        // ===

        // Create objects:
        Grid grid = new Grid(GRID_FILE_PATH, jewelTypeMap);
        Commands commands = new Commands(COMMANDS_FILE_PATH);
        Leaderboard leaderboard = new Leaderboard(LEADERBOARD_FILE_PATH);
        Output output = new Output(OUTPUT_FILE_PATH);
        // ===

        // Create and Run game:
        Game game = new Game(grid, commands, leaderboard, output);
        game.run();
        game.writeOutput();
        // ===
    }
}
