import javafx.scene.Scene;

/**
 * A base abstract class for windows. Windows must have a method that returns a scene to show.
 * Assumes setScene method is used in their constructors to initialize scene.
 */
public abstract class Window {

    private Scene scene;
    private final AppManager appManager;

    public Window(AppManager appManager) {
        this.appManager = appManager;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public AppManager getAppManager() {
        return appManager;
    }
}
