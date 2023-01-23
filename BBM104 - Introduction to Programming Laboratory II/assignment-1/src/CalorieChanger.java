public abstract class CalorieChanger {

    // Fields:
    private final String ID;
    private final String NAME;
    private final int CALORIE;

    // Constructor:
    CalorieChanger(String id, String name, int calorie) {
        this.ID = id;
        this.NAME = name;
        this.CALORIE = calorie;
    }

    // Methods:
    public final int getCALORIE() {
        return CALORIE;
    }

    public final String getNAME() {
        return NAME;
    }
}
