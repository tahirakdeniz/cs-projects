public class SelectionSort implements Sorter{
    public long count;
    @Override
    public void sort(int[] arr) {
        int n = arr.length;

        for(int i=0; i < n-1; i++){
            int min = i;
            count++;
            for(int j = i+1; j<n; j++){
                count++;
                if(arr[min] > arr[j]){
                    min = j;
                    count++;
                }
            }
            if(min != i){
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
                count++;
            }
        }
    }

    @Override
    public String getName() {
        return "Selection Sort";
    }
}
