import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Planner {

    public final Task[] taskArray;
    public final Integer[] compatibility;
    public final Double[] maxWeight;
    public final ArrayList<Task> planDynamic;
    public final ArrayList<Task> planGreedy;

    public Planner(Task[] taskArray) {

        // Should be instantiated with an Task array
        // All the properties of this class should be initialized here

        this.taskArray = taskArray;
        this.compatibility = new Integer[taskArray.length];
        maxWeight = new Double[taskArray.length];

        this.planDynamic = new ArrayList<>();
        this.planGreedy = new ArrayList<>();

        calculateCompatibility();
        System.out.println("Calculating max array");
        System.out.println("---------------------");
        calculateMaxWeight(taskArray.length - 1);
        System.out.println();
    }

    /**
     * @param index of the {@link Task}
     * @return Returns the index of the last compatible {@link Task},
     * returns -1 if there are no compatible {@link Task}s.
     */
    public int binarySearch(int index) {
        // Finding the last compatible task using binary search
        String startTime = taskArray[index].getStartTime();

        int left = 0;
        int right = index - 1;
        int compatibleIndex = -1;

        while (left <= right) {
            int mid = (left + right) / 2;
            if (taskArray[mid].getFinishTime().compareTo(startTime) <= 0) {
                compatibleIndex = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return compatibleIndex;
    }


    /**
     * {@link #compatibility} must be filled after calling this method
     */
    public void calculateCompatibility() {
        if(compatibility[0] != null) // if calculateCompatibility run before, don't run again.
            return;

        // Filling the compatibility array for each task
        for (int i = 0; i < taskArray.length; i++) {
            compatibility[i] = binarySearch(i);
        }
    }


    /**
     * Uses {@link #taskArray} property
     * This function is for generating a plan using the dynamic programming approach.
     * @return Returns a list of planned tasks.
     */
    public ArrayList<Task> planDynamic() {
        System.out.println("Calculating the dynamic solution");
        System.out.println("--------------------------------");
        solveDynamic(taskArray.length - 1);
        System.out.println();
        System.out.println("Dynamic Schedule");
        System.out.println("----------------");
        for (Task task : planDynamic) {
            System.out.println("At " + task.getStartTime() + ", " + task.getName() + ".");
        }
        return planDynamic;
    }

    /**
     * {@link #planDynamic} must be filled after calling this method
     */
    public void solveDynamic(int i) {

        if(i < 0){
            return;
        }
        System.out.println("Called solveDynamic(" + i +")");

        double maxWeightWithI = taskArray[i].getWeight();
        maxWeightWithI += compatibility[i] >= 0 ? maxWeight[compatibility[i]] : 0;
        double maxWeightWithoutI = i>=1 ? maxWeight[i - 1] : 0;

        if(maxWeightWithI >= maxWeightWithoutI){
            solveDynamic(compatibility[i]);
            planDynamic.add(taskArray[i]);
        }
        else{
            solveDynamic(i-1);
        }
    }

    /**
     * {@link #maxWeight} must be filled after calling this method
     */
    /* This function calculates maximum weights and prints out whether it has been called before or not  */
    public Double calculateMaxWeight(int i) {
        System.out.println("Called calculateMaxWeight("+i+")");

        if (i>0 && maxWeight[i] != null ) {
            return maxWeight[i]; // calculateMaxWeight is called before for task i
        }

        if(i < 0) // base case
            return 0d;

        double maxWeightIncludingTask = taskArray[i].getWeight(); // Case 1: task i is in the solution
        int compatibleTaskIndex = compatibility[i]; // Get index of the first compatible task

        maxWeightIncludingTask += calculateMaxWeight(compatibleTaskIndex); // Combine with result of recursive call


        double maxWeightExcludingTask = calculateMaxWeight(i - 1); // Case 2: task i is not in the solution
        maxWeight[i] = Math.max(maxWeightIncludingTask, maxWeightExcludingTask); // Take the maximum of the two cases

        return maxWeight[i];
    }

    /**
     * {@link #planGreedy} must be filled after calling this method
     * Uses {@link #taskArray} property
     *
     * @return Returns a list of scheduled assignments
     */

    /*
     * This function is for generating a plan using the greedy approach.
     * */
    public ArrayList<Task> planGreedy() {
        System.out.println("Greedy Schedule");
        System.out.println("---------------");
        Task lastAdded = taskArray[0];
        planGreedy.add(lastAdded);
        System.out.println("At " + taskArray[0].getStartTime() + ", " + taskArray[0].getName() + ".");
        for (int i = 1; i < taskArray.length; i++) {
            Task task = taskArray[i];
            if(task.getStartTime().compareTo(lastAdded.getFinishTime()) >= 0){
                lastAdded = taskArray[i];
                planGreedy.add(lastAdded);
                System.out.println("At " + taskArray[i].getStartTime() + ", " + taskArray[i].getName()+ ".");
            }
        }
        return planGreedy;
    }
}
