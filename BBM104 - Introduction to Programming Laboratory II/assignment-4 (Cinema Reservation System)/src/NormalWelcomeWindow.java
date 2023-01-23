import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class NormalWelcomeWindow extends WelcomeWindow {

    public NormalWelcomeWindow(AppManager appManager) {
        super(appManager);
    }

    @Override
    protected Parent getSpecialButtonsParent() {
        // Empty Parent:
        return new StackPane();
    }

    @Override
    protected String getSpecialType() {
        return "User";
    }

    @Override
    protected String getSpecialDescription() {
        return "Select a film and then click 'OK' to continue.";
    }

}
