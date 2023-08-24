import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayData {
    private final List<Integer> values = new ArrayList<>();

    public int[] getArray(int size){
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = values.get(i);
        }
        return array;
    }

    public int[] getSortedArray(int size){
        int[] arr = getArray(size);
        Arrays.sort(arr);
        return arr;
    }

    public int[] getReverselySortedArray(int size){
        int[] arr = getArray(size);
        Arrays.sort(arr);
        int n = arr.length;
        for (int i = 0; i < n / 2; i++) {
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = temp;
        }
        return arr;
    }

    public void readCSVFile(String csvFile){
        String line = "";
        String csvSplitBy = ",";
        int columnIndex = 6;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(csvSplitBy);
                values.add(Integer.parseInt(fields[columnIndex]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
