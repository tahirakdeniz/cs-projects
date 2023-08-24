import java.util.Random;

public class SearchExperiment {
    private static final String X_AXIS_TITLE = "Input sizes";
    private static final String Y_AXIS_TITLE = "Time in nanoseconds";
    public static final int ITERATION_NUMBER = 1000;

    private final Searcher linearSearch;
    private final Searcher binarySearch;
    private final int[] inputSizes;

    public SearchExperiment(int[] inputSizes, Searcher linearSearch, Searcher binarySearch) {
        this.linearSearch = linearSearch;
        this.binarySearch = binarySearch;
        this.inputSizes = inputSizes;
    }

    public void runExperiment(String title, int[][] randomArr, int[][] sortedArr){
        System.out.println("\n\nBINARY SEARCH: ");
        double[] binarySearchResults = getTimingResults(sortedArr, binarySearch);

        System.out.println("\n\nLINEAR SEARCH (SORTED): ");
        double[] linearSearchSortedResults = getTimingResults(sortedArr, linearSearch);

        System.out.println("\n\nLINEAR SEARCH (RANDOM): ");
        double[] linearSearchRandomResults = getTimingResults(randomArr, linearSearch);

        Chart chart = new Chart(title, inputSizes, X_AXIS_TITLE, Y_AXIS_TITLE);
        chart.addData("Linear search (random data)", linearSearchRandomResults);
        chart.addData("Linear search (sorted data)", linearSearchSortedResults);
        chart.addData("Binary search (sorted data)", binarySearchResults);
        chart.show();
        chart.save();
    }

    private static double[] getTimingResults(int[][] arrParam, Searcher searcher){
        int[][] experimentArrays = arrParam.clone();
        int length = experimentArrays.length;
        double[] timesArr = new double[length];

        for (int arrIndex = 0; arrIndex< length; arrIndex++) {
            double sum = 0;
            for (int i = 0; i < ITERATION_NUMBER; i++) {
                int[] arr = (experimentArrays[arrIndex]).clone();
                int value = getValueToSearch(arr);
                long t1 = System.nanoTime();
                int test = searcher.Search(arr, value);
                sum += System.nanoTime()-t1;
                if(test == -1)
                    throw new RuntimeException("Item was not found.");
            }
            sum = sum/ITERATION_NUMBER;
            timesArr[arrIndex] = sum;
            System.out.println(experimentArrays[arrIndex].length + "\t TIME:" + sum);
        }
        return timesArr;
    }

    private static int getValueToSearch(int[] arr) {
        Random random = new Random();
        int index = random.nextInt(arr.length);
        return arr[index];
    }
}
