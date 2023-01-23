import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RemoveFilmWindow extends Window{
    public RemoveFilmWindow(AppManager appManager) {
        super(appManager);

        // Text:
        Text descriptionText = new Text("Select the film that you desire to remove and then click OK");
        HBox textHBox = new HBox(descriptionText);
        textHBox.setAlignment(Pos.CENTER);

        // Dropdown List:
        ObservableList<Film> filmObservableList = appManager.getDataManager().getFilmObservableList();
        ComboBox<Film> filmComboBox = new ComboBox<>(filmObservableList);
        filmComboBox.setMinWidth(300);

        if(filmObservableList.size()>0)
            filmComboBox.setValue(filmObservableList.get(0));

        // Buttons:
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            appManager.showWelcomeWindow();
        });

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            Film film = filmComboBox.getValue();

            if(film == null)
                return;

            appManager.getDataManager().removeFilm(film);
            filmObservableList.remove(film);

           if(filmObservableList.size()>0)
                filmComboBox.setValue(filmObservableList.get(0));
        });

        HBox buttonsHBox = new HBox(backButton, okButton);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(20);

        // VBox - root.
        VBox vBox = new VBox(textHBox, filmComboBox, buttonsHBox);
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        setScene(scene);

    }
}
