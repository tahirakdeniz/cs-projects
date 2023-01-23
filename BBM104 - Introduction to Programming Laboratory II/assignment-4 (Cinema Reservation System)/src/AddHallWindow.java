import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.util.Arrays;

/**
 * A class to show Add Hall Window.
 */
public class AddHallWindow extends Window {

    public AddHallWindow(AppManager appManager) {
        super(appManager);

        Film currentFilm = appManager.getCurrentFilm();

        Text text = new Text(currentFilm.getName() + "(" + currentFilm.getDuration()+ ")") ;

        // ROW:
        Text rowText = new Text("Row: ");
        ComboBox<Integer> rowComboBox = new ComboBox<>(FXCollections.observableArrayList(Arrays.asList(3,4,5,6,7,8,9,10)));
        rowComboBox.setValue(3);

        // COLUMN:
        Text columnText = new Text("Column: ");
        ComboBox<Integer> colComboBox = new ComboBox<>(FXCollections.observableArrayList(Arrays.asList(3,4,5,6,7,8,9,10)));
        colComboBox.setValue(3);

        // NAME:
        Text nameText = new Text("Name: ");
        TextField nameTextField = new TextField();

        // PRICE
        Text priceText = new Text("Price: ");
        TextField priceTextField = new TextField();

        // BUTTONS:
        Button backButton = new Button("Back");
        backButton.setOnAction(event -> {
            appManager.showFilmWindow();
        });

        Text targetText = new Text();

        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            String hallName = nameTextField.getText();
            String priceString = priceTextField.getText();

            try {

                int rowNumber = rowComboBox.getValue();
                int colNumber = colComboBox.getValue();

                if (priceString.equals("") || hallName.equals("")) {
                    throw new NullFieldException();
                }

                double pricePerSeat;

                try{
                    pricePerSeat = Double.parseDouble(priceString);
                }
                catch (NumberFormatException e) {
                    throw new AppException("ERROR: Please Enter a double to price");
                }

                Hall hall = new Hall(hallName, pricePerSeat, rowNumber, colNumber);
                appManager.getDataManager().addHall(appManager.getCurrentFilm(),hall);

                targetText.setText("");

                nameTextField.clear();
                priceTextField.clear();
            }
            catch (AppException e){
                targetText.setText(e.getMessage());
            }

        });

        HBox okButtonHBox = new HBox(okButton);
        okButtonHBox.setAlignment(Pos.CENTER_RIGHT);

        // GRID PANE
        GridPane gridPane = new GridPane();
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        gridPane.addRow(0, rowText, rowComboBox);
        gridPane.addRow(1, columnText, colComboBox);
        gridPane.addRow(2, nameText, nameTextField);
        gridPane.addRow(3, priceText, priceTextField);
        gridPane.addRow(4, backButton, okButtonHBox);

        // Scene and Root.:
        VBox vBox = new VBox(text, gridPane, targetText);
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(20);

        Scene scene = new Scene(vBox);
        setScene(scene);
    }
}
