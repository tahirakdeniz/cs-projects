public class App {
    private final int[] inputSizes;
    private final ArrayData arrayData;

    public App(int[] inputSizes, String dataFile) {
        this.inputSizes = inputSizes;

        arrayData = new ArrayData();
        arrayData.readCSVFile(dataFile);
    }

    public void run(){
        int[][] randomArrays = generateRandomArr();
        int[][] sortedArrays = generateSortedArr();
        int[][] reverselySortedArrays = generateReverselySortedArr();

        Sorter bucketSort = new BucketSort();
        Sorter quickSort = new QuickSort();
        Sorter selectionSort = new SelectionSort();

        SortExperiment classicExperiment = new SortExperiment(inputSizes, bucketSort, quickSort, selectionSort);
        classicExperiment.runExperiment("Test on Random Data", randomArrays);
        classicExperiment.runExperiment("Test on Sorted Data", sortedArrays);
        classicExperiment.runExperiment("Test on Reversely Sorted Data", reverselySortedArrays);

        System.out.println("SORTING EXPERIMENT HAS BEEN COMPLETED");

        Searcher linearSearch = new LinearSearch();
        Searcher binarySearch = new BinarySearch();

        SearchExperiment searchExperiment = new SearchExperiment(inputSizes, linearSearch, binarySearch);
        searchExperiment.runExperiment("Test Search Algorithms", randomArrays, sortedArrays);
    }

    private int[][] generateRandomArr(){
        int[][] arr = new int[inputSizes.length][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrayData.getArray(inputSizes[i]);
        }
        return arr;
    }

    private int[][] generateSortedArr(){
        int[][] arr = new int[inputSizes.length][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrayData.getSortedArray(inputSizes[i]);
        }
        return arr;
    }

    private int[][] generateReverselySortedArr(){
        int[][] arr = new int[inputSizes.length][];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arrayData.getReverselySortedArray(inputSizes[i]);
        }
        return arr;
    }
}
