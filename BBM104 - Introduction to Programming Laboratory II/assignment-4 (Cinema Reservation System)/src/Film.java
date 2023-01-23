public class Film {
    private final String name;
    private final String trailerPath;
    private final int duration;

    public Film(String name, String trailerPath, int duration) {
        this.name = name;
        this.trailerPath = trailerPath;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public String getTrailerPath() {
        return trailerPath;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return name;
    }

}
