public class SortExperiment {
    private static final String X_AXIS_TITLE = "Input sizes";
    private static final String Y_AXIS_TITLE = "Time in milliseconds";
    public static final int ITERATION_NUMBER = 10;

    private final Sorter[] sorters;
    private final int[] inputSizes;

    public SortExperiment(int[] inputSizes, Sorter ...sorters) {
        this.sorters = sorters;
        this.inputSizes = inputSizes;
    }

    public void runExperiment(String title, int[][] arr){
        Chart chart = new Chart(title, inputSizes, X_AXIS_TITLE, Y_AXIS_TITLE);
        for (Sorter sorter :
                sorters) {
            chart.addData(sorter.getName(), getTimingResults(arr, sorter));
        }
        chart.show();
        chart.save();
    }

    private static double[] getTimingResults(int[][] arrParam, Sorter sorter){
        int[][] arrays = arrParam.clone();
        int length = arrays.length;
        double[] timesArr = new double[length];
        for (int arrIndex = 0; arrIndex< length; arrIndex++) {
            double sum = 0;
            for (int i = 0; i < ITERATION_NUMBER; i++) {
                int[] arr = (arrays[arrIndex]).clone();
                long startTime = System.currentTimeMillis();
                sorter.sort(arr);
                sum += System.currentTimeMillis()-startTime;
            }
            sum = sum/ITERATION_NUMBER;
            timesArr[arrIndex] = sum;
            System.out.println(sorter.getName() + "\t" + arrays[arrIndex].length + "\t TIME: " + sum);
        }
        return timesArr;
    }
}
