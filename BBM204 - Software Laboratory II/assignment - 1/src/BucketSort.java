import java.util.ArrayList;
import java.util.Collections;

public class BucketSort implements Sorter {

    @Override
    public void sort(int[] arr) {
        int numberOfBuckets = (int) Math.sqrt(arr.length);

        ArrayList<Integer>[] buckets = new ArrayList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++)
            buckets[i] = new ArrayList<>();

        int max = Integer.MIN_VALUE;
        for (int item :
                arr) {
            max = Math.max(item, max);
        }

        for (int item : arr) // add items to buckets
            buckets[hash(item, max, numberOfBuckets)].add(item);

        for (ArrayList<Integer> bucket : buckets) // sort each bucket
            Collections.sort(bucket);

        int index = 0;
        for (int i = 0; i < numberOfBuckets; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i].get(j); // concat buckets
            }
        }
    }

    @Override
    public String getName() {
        return "Bucket Sort";
    }

    private int hash(int i, int max, int numberOfBuckets){
        return i/max*(numberOfBuckets - 1);
    }
}
