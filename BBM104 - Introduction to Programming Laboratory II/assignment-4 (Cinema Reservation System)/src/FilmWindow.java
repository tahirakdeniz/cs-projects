import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.File;

public class FilmWindow extends Window {

    public FilmWindow(AppManager appManager) {
        super(appManager);

        Film currentFilm = getAppManager().getCurrentFilm();

        Text descriptionText = new Text(currentFilm.getName() + " (" + currentFilm.getDuration() + " minutes)");

        // Media:
        //TODO PATH
        Media media = new Media(new File( "assets/trailers/"+ currentFilm.getTrailerPath()).toURI().toString());

        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);

        mediaView.setFitHeight(600);
        mediaView.setFitWidth(600);

        // Buttons:
        final Boolean[] isMediaPlayerPlaying = {false};

        Button playButton = new Button("Play");
        playButton.setOnAction(event -> {
            if(isMediaPlayerPlaying[0]) {
                mediaPlayer.pause();
                isMediaPlayerPlaying[0] = false;
                playButton.setText("Play");
            }

            else {
                mediaPlayer.play();
                isMediaPlayerPlaying[0] = true;
                playButton.setText("Pause");
            }
        });

        Button skipBackButton = new Button("Skip Back");
        skipBackButton.setOnAction(event -> {
            Duration time = Duration.seconds(mediaPlayer.getCurrentTime().toSeconds() - 5);
            mediaPlayer.seek(time);
            mediaPlayer.play();
        });

        Button skipForwardButton = new Button("Skip Forward");
        skipForwardButton.setOnAction(event -> {
            Duration time = Duration.seconds(mediaPlayer.getCurrentTime().toSeconds() + 5);
            mediaPlayer.seek(time);
            mediaPlayer.play();
        });

        Button rewindButton = new Button("Rewind Button");
        rewindButton.setOnAction(event -> mediaPlayer.seek(Duration.ZERO));

        //Slider for Volume:
        Slider volumeSlider = new Slider();
        volumeSlider.setOrientation(Orientation.VERTICAL);
        volumeSlider.setValue(50);
        mediaPlayer.volumeProperty().bind(volumeSlider.valueProperty().divide(100));

        VBox mediaButtonsVBox = new VBox(playButton, rewindButton,skipForwardButton, skipBackButton, volumeSlider);
        mediaButtonsVBox.setAlignment(Pos.CENTER);
        mediaButtonsVBox.setSpacing(20);

        HBox mediaHBox = new HBox(mediaView, mediaButtonsVBox);
        mediaHBox.setAlignment(Pos.CENTER);
        mediaHBox.setSpacing(10);

        // Dropdown list:
        ObservableList<Hall> hallObservableList = appManager.getDataManager().getHallObservableList(appManager.getCurrentFilm());
        ComboBox<Hall> hallComboBox = new ComboBox<>(hallObservableList);

        if(hallObservableList.size() > 0){
            hallComboBox.setValue(hallObservableList.get(0));
        }

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            //TODO Write Here
            Hall hall = hallComboBox.getValue();

            if (hall == null)
                return;

            appManager.setCurrentHall(hall);
            appManager.showHallWindow();
            mediaPlayer.stop();
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            appManager.showWelcomeWindow();
            mediaPlayer.stop();
        });

        Button addHallButton = new Button("Add Hall");
        addHallButton.setOnAction(event -> {
            getAppManager().showAddHallWindow();
            mediaPlayer.stop();
        });

        Button removeHallButton = new Button("Remove Hall");
        removeHallButton.setOnAction(event -> {
            appManager.showRemoveHallWindow();
            mediaPlayer.stop();
        });

        HBox adminHBox = new HBox();
        if(appManager.getCurrentUser().isAdmin()) {
            adminHBox = new HBox(addHallButton, removeHallButton);
            adminHBox.setSpacing(30);
        }

        HBox buttonsBox = new HBox(backButton, adminHBox, hallComboBox, okButton);
        buttonsBox.setAlignment(Pos.CENTER);
        buttonsBox.setSpacing(30);
        // ---

        // Scene and Root:
        VBox vBox = new VBox(descriptionText,mediaHBox, buttonsBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setPadding(new Insets(40));
        vBox.setSpacing(10);
        Scene scene = new Scene(vBox);
        setScene(scene);

    }


}
