public class QuickSort implements Sorter {
    public long count;
    @Override
    public void sort(int[] arr) {

        int[] stack = new int[arr.length];
        int top = -1;
        stack[++top] = 0;
        stack[++top] = arr.length -1;

        while(top>=0){
            int high = stack[top--];
            int low = stack[top--];

            int pivot = partition(arr, low, high);
            if(pivot - 1 > low){
                stack[++top] = low;
                stack[++top] = pivot-1;
                count++;
            }
            if(pivot + 1 < high){
                stack[++top] = pivot + 1;
                stack[++top] = high;
                count++;
            }
        }
    }

    @Override
    public String getName() {
        return "Quick Sort";
    }

    private int partition(int[] arr, int lowIndex, int highIndex){
        int pivot = arr[highIndex];
        int i = lowIndex - 1;

        for(int j = lowIndex; j < highIndex; j++) {
            if(arr[j] <= pivot){
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
                count++;
            }
            count++;
        }
        int temp = arr[i+1];
        arr[i+1] = arr[highIndex];
        arr[highIndex] = temp;
        count++;
        return i+1;
    }
}
