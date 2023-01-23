import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

// TODO write javadoc comments

/**
 * A class to store variables and methods needed to run application.
 */
public class AppManager {

    // Fields:
    private Stage stage;

    private User currentUser;
    private Film currentFilm;
    private Hall currentHall;

    private LoginWindow loginWindow;
    private SignupWindow signupWindow;
    private AddFilmWindow addFilmWindow;
    private RemoveFilmWindow removeFilmWindow;

    private final UserDataManager userDataManager;
    private final CinemaDataManager cinemaDataManager;
    private final PropertiesReader propertiesReader;

    private int wrongAnswerNumber = 0;

    public AppManager(UserDataManager userDataManager, CinemaDataManager cinemaDataManager, PropertiesReader propertiesReader) {
        this.userDataManager = userDataManager;
        this.cinemaDataManager = cinemaDataManager;
        this.propertiesReader = propertiesReader;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
        stage.setTitle(propertiesReader.getTitle());

        stage.getIcons().add(new Image(new File("assets/icons/logo.png").toURI().toString()));
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }


    public Film getCurrentFilm() {
        return currentFilm;
    }

    public void setCurrentFilm(Film currentFilm) {
        this.currentFilm = currentFilm;
    }

    public Hall getCurrentHall() {
        return currentHall;
    }

    public void setCurrentHall(Hall currentHall) {
        this.currentHall = currentHall;
    }

    public int getWrongAnswerNumber() {
        return wrongAnswerNumber;
    }

    public void setWrongAnswerNumber(int wrongAnswerNumber) {
        this.wrongAnswerNumber = wrongAnswerNumber;
    }

    public UserDataManager getUserManager() {
        return userDataManager;
    }

    public CinemaDataManager getDataManager() {
        return cinemaDataManager;
    }

    public PropertiesReader getPropertiesReader() {
        return propertiesReader;
    }

    public void showLoginWindow(){
        if (loginWindow == null)
            loginWindow = new LoginWindow(this);

        showScene(loginWindow.getScene());
    }

    public void showSignupWindow(){
        SignupWindow signupWindow = new SignupWindow(this);
        showScene(signupWindow.getScene());
    }

    public void showWelcomeWindow() {
        WelcomeWindow welcomeWindow;
        if(currentUser.isAdmin())
            welcomeWindow = new AdminWelcomeWindow(this);
        else
            welcomeWindow = new NormalWelcomeWindow(this);

        showScene(welcomeWindow.getScene());
    }

    public void showAddFilmWindow() {
        AddFilmWindow addFilmWindow = new AddFilmWindow(this);
        showScene(addFilmWindow.getScene());
    }

    public void showRemoveFilmWindow() {
        RemoveFilmWindow removeFilmWindow  = new RemoveFilmWindow(this);
        showScene(removeFilmWindow.getScene());
    }

    public void showEditUserWindow() {
        EditUserWindow editUserWindow = new EditUserWindow(this);
        showScene(editUserWindow.getScene());
    }

    public void showFilmWindow() {
        FilmWindow filmWindow = new FilmWindow(this);
        showScene(filmWindow.getScene());
    }

    public void showAddHallWindow(){
        AddHallWindow addHallWindow = new AddHallWindow(this);
        showScene(addHallWindow.getScene());
    }

    public void showHallWindow() {
        AdminHallWindow adminHallWindow = new AdminHallWindow(this);
        showScene(adminHallWindow.getScene());
    }
    // TODO ADD OTHER WINDOWS

    public void showRemoveHallWindow() {
        RemoveHallWindow removeHallWindow = new RemoveHallWindow(this);
        showScene(removeHallWindow.getScene());
    }

    private void showScene(Scene scene) {
        stage.setScene(scene);
        stage.show();
    }

    public void playErrorSound() {
        Media media = new Media(new File("assets/effects/error.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }

}
