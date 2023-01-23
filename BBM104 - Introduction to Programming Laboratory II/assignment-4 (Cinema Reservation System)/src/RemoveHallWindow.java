import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class RemoveHallWindow extends Window{
    public RemoveHallWindow(AppManager appManager) {
        super(appManager);

        // Text:
        Text descriptionText = new Text("Select the hall that you desire to remove and then click OK");
        HBox textHBox = new HBox(descriptionText);
        textHBox.setAlignment(Pos.CENTER);

        // Dropdown List:
        ObservableList<Hall> hallObservableList = appManager.getDataManager().getHallObservableList(appManager.getCurrentFilm());
        ComboBox<Hall> hallComboBox = new ComboBox<>(hallObservableList);
        hallComboBox.setMinWidth(300);

        if(hallObservableList.size()>0)
            hallComboBox.setValue(hallObservableList.get(0));

        // Buttons:
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            appManager.showFilmWindow();
        });

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            Hall hall= hallComboBox.getValue();

            if(hall == null)
                return;

            appManager.getDataManager().removeHall(appManager.getCurrentFilm(), hall);
            hallObservableList.remove(hall);

            if(hallObservableList.size()>0)
                hallComboBox.setValue(hallObservableList.get(0));
        });

        HBox buttonsHBox = new HBox(backButton, okButton);
        buttonsHBox.setAlignment(Pos.CENTER);
        buttonsHBox.setSpacing(20);

        // VBox - root.
        VBox vBox = new VBox(textHBox, hallComboBox, buttonsHBox);
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        setScene(scene);
    }
}
