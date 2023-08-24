import java.time.LocalTime;

public class Task implements Comparable<Task> {
    public String name;
    public String start;
    public int duration;
    public int importance;
    public boolean urgent;

    /*
        Getter methods
     */
    public String getName() {
        return this.name;
    }

    public String getStartTime() {
        return this.start;
    }

    public int getDuration() {
        return this.duration;
    }

    public int getImportance() {
        return this.importance;
    }

    public boolean isUrgent() {
        return this.urgent;
    }

    /**
     * Finish time should be calculated here
     *
     * @return calculated finish time as String
     */
    public String getFinishTime() {
        // Extracting hours and minutes from the start time
        String[] startTokens = start.split(":");
        int startHour = Integer.parseInt(startTokens[0]);
        int startMinute = Integer.parseInt(startTokens[1]);

        // Calculating finish time by adding duration to start time
        int finishHour = (startHour + duration) % 24;

        // Converting finish time back to string in the same format as start time
        return String.format("%02d:%02d", finishHour, startMinute);
    }

    /**
     * Weight calculation should be performed here
     *
     * @return calculated weight
     */
    public double getWeight() {
        return (importance * (urgent ? 2000 : 1)) / (double) duration;
    }

    /**
     * This method is needed to use {@link java.util.Arrays#sort(Object[])} ()}, which sorts the given array easily
     *
     * @param o Object to compare to
     * @return If self > object, return > 0 (e.g. 1)
     * If self == object, return 0
     * If self < object, return < 0 (e.g. -1)
     */
    @Override
    public int compareTo(Task o) {
        String thisFinishTime = getFinishTime();
        String otherFinishTime = o.getFinishTime();

        return thisFinishTime.compareTo(otherFinishTime);
    }
}
