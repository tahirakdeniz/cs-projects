import javafx.application.Application;
import javafx.stage.Stage;


/**
 * A subclass of Application class to start the application. There are
 * some overridden methods to initialize, start and stop application.
 */
public class App extends Application {

    private final AppManager appManager;
    private final BackupFileIO backupFileIO;

    public App() {
        //TODO PATH
        backupFileIO = new BackupFileIO("src/assets/data/backup.dat");
        PropertiesReader propertiesReader = new PropertiesReader("src/assets/data/properties.dat");

        appManager = new AppManager(backupFileIO.getUserManager(), backupFileIO.getCinemaDataManager(), propertiesReader);

    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        appManager.setStage(primaryStage);
        appManager.showLoginWindow();
    }

    @Override
    public void stop() throws Exception {
        // backupFileIO.writeToFile();
        super.stop();
        System.out.println("STOPPED");
    }

}
