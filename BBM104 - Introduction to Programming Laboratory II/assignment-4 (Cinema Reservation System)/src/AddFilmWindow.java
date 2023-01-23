import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A Window to show add film window. It is used to add films.
 */
public class AddFilmWindow extends Window {
    public AddFilmWindow(AppManager appManager) {
        super(appManager);

        // Text:
        Text descriptionText = new Text("Please give name, relative path of the trailer and duration of the film.");
        VBox textVbox = new VBox(descriptionText);
        textVbox.setAlignment(Pos.CENTER);

        // GridPane:
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setAlignment(Pos.CENTER);

        // For Name:
        Label nameLabel = new Label("Name:");
        TextField nameTextField = new TextField();
        gridPane.addRow(0, nameLabel, nameTextField);

        // For trailer path:
        Label trailerPathLabel = new Label("Trailer (Relative Path): ");
        TextField trailerPathTextField = new TextField();
        gridPane.addRow(1, trailerPathLabel, trailerPathTextField);

        // For duration:
        Label durationLabel = new Label("Duration (m): ");
        TextField durationTextField = new TextField();
        gridPane.addRow(2, durationLabel, durationTextField);

        // Back Button:
        Button backButton = new Button("BACK");
        backButton.setOnAction(event -> {
            appManager.showWelcomeWindow();
        });

        // TARGET TEXT:
        Text targetText = new Text();
        HBox targetTextHBox = new HBox(targetText);
        targetTextHBox.setAlignment(Pos.CENTER);

        // Ok Button:
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {

            String name = nameTextField.getText();
            String trailerPath = trailerPathTextField.getText();
            String durationString = durationTextField.getText();

            try {
                if (name.equals("") || trailerPath.equals("") || durationString.equals("")) {
                    throw new NullFieldException();
                }

                int duration;
                try{
                    duration = Integer.parseInt(durationString);
                }
                catch (NumberFormatException e) {
                    throw  new AppException("ERROR: Please enter a integer for duration");
                }

                Film film = new Film(name, trailerPath, duration);
                appManager.getDataManager().addFilm(film);

                // Clear Fields:
                nameTextField.clear();
                trailerPathTextField.clear();
                durationTextField.clear();

                targetText.setText("");
            } catch (AppException e) {
                getAppManager().playErrorSound();
                targetText.setText(e.getMessage());
            }
        });

        HBox okButtonHBox = new HBox(okButton);
        okButtonHBox.setAlignment(Pos.CENTER_RIGHT);

        gridPane.addRow(3, backButton, okButtonHBox);

        // BorderPane:
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding( new Insets(30));

        borderPane.setTop(textVbox);
        borderPane.setCenter(gridPane);
        borderPane.setBottom(targetTextHBox);

        // Set Scene:
        Scene scene = new Scene(borderPane);
        setScene(scene);
    }
}
