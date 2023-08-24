import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args){

        // Step 0 - read the input parameters
        DatReader datReader = new DatReader(args[0]);

        String grid_input_file_name = datReader.getStringVar("grid_input_file_name");
        int num_rows = datReader.getIntVar("num_rows");
        int num_cols= datReader.getIntVar("num_cols");
        Point mission_0_source = datReader.getPointVar("mission_0_source");
        Point mission_0_destination= datReader.getPointVar("mission_0_destination");
        Point mission_1_source = datReader.getPointVar("mission_1_source");
        int max_flying_height= datReader.getIntVar("max_flying_height");
        Double fuel_cost_per_unit= datReader.getDoubleVar("fuel_cost_per_unit");
        Double climbing_cost_per_unit=datReader.getDoubleVar("climbing_cost_per_unit");

        // Step 1 - construct map data
        IMECEPathFinder map = new IMECEPathFinder(grid_input_file_name, num_rows, num_cols, max_flying_height, fuel_cost_per_unit, climbing_cost_per_unit);


        // Step 2 - construct DrawingPanel, and get its Graphics context
        /**
          * 0 points if left uncommented
          * */
        // COMMENT OUT THESE LINES BEFORE TURBO TESTING AND SUBMISSION
        DrawingPanel panel = new DrawingPanel(num_rows, num_cols);
        Graphics g = panel.getGraphics();
        map.drawGrayscaleMap(g);
        /**
          * 0 points if left uncommented
          * */

        // Step 3 - get the most cost-efficient path between source and destination Points
        System.out.println("########## Mission 0 ##########");
        List<Point> shortestPath = map.getMostEfficientPath(mission_0_source, mission_0_destination);
        if (shortestPath.size() == 0){
            System.out.println("ERROR PathNotFound: There is no most cost-efficient path that meets all criteria!");
        }
        else{
            System.out.println("The most cost-efficient path's size: " + shortestPath.size());
            double totalCost = map.getMostEfficientPathCost(shortestPath);
            System.out.println("The most cost-efficient path has a cost of: " + totalCost);

            /**
             * 0 points if left uncommented
             * */

            map.drawMostEfficientPath(g, shortestPath); // COMMENT OUT THIS LINE BEFORE TURBO TESTING AND SUBMISSION

            /**
             * 0 points if left uncommented
             * */
        }

        // Step 4 - get the lowest elevation Escape Path towards the West from a source Point
        System.out.println("########## Mission 1 ##########");
        List<Point> leastElevationPath = map.getLowestElevationEscapePath(mission_1_source);
        System.out.println("The size of the escape path with the least elevation cost: " + leastElevationPath.size());
        int totalChange = map.getLowestElevationEscapePathCost(leastElevationPath);
        System.out.println("The escape path has the least elevation cost of: " + totalChange);

        /**
         * 0 points if left uncommented
         * */

        map.drawLowestElevationEscapePath(g, leastElevationPath);  // COMMENT OUT THIS LINE BEFORE TURBO TESTING AND SUBMISSION

        /**
         * 0 points if left uncommented
         * */


        /**
         * 0 points if left uncommented
         * */
        // Extra example for drawing map
        // COMMENT OUT THESE LINES BEFORE TURBO TESTING AND SUBMISSION
        DrawingPanel panel2 = new DrawingPanel(473, 347);
        Graphics g2 = panel2.getGraphics();
        drawFunny(g2, 347, 473, "semester.txt");
        /**
         * 0 points if left uncommented
         * */
    }

    public static void drawFunny(Graphics g, int height, int width, String filename) {
        int[][] grid = new int[height][width];
        Scanner sc;
        try {
            sc = new Scanner(new File(filename));
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (sc.hasNextInt()) {
                        grid[i][j] = sc.nextInt();
                    }
                }
            }

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    int value = grid[i][j];
                    g.setColor(new Color(value, value, value));
                    g.fillRect(j, i, 1, 1);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
