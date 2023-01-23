import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.File;
import java.util.Arrays;

public class AdminHallWindow extends Window{
     public AdminHallWindow(AppManager appManager) {
        super(appManager);

        User currentUser = appManager.getCurrentUser();

        ObservableList<User> userObservableList = getAppManager().getUserManager().getUserObservableList();
        ComboBox<User> userComboBox = new ComboBox<>(userObservableList);

        Hall currentHall = getAppManager().getCurrentHall();
        Seat[][] seats = getAppManager().getDataManager().getSeatArray(currentHall);

        GridPane gridPane = new GridPane();

        for (int row = 0; row < seats.length; row++) {
            Seat[] seatColumns = seats[row];
            System.out.println(Arrays.toString(seatColumns));
            for (int column = 0; column < seatColumns.length; column++) {

                // TODO PATH:
                Image emptySeatImage = new Image(new File("assets/icons/empty_seat.png").toURI().toString());
                Image reversedSeatImage = new Image(new File("assets/icons/reserved_seat.png").toURI().toString());

                Seat seat = seatColumns[column];
                ImageView seatImageView;

                System.out.println(seat.getOwner());
                if (seat.getOwner() == null){
                    seatImageView = new ImageView(emptySeatImage);
                }
                else {
                    seatImageView = new ImageView(reversedSeatImage);
                }

                seatImageView.setFitHeight(50);
                seatImageView.setFitWidth(50);

                Button button = new Button();
                button.setGraphic(seatImageView);

                if(seat.getOwner() != currentUser && !currentUser.isAdmin()){
                    button.setDisable(true);
                }

                button.setOnAction(event -> {
                    User user;
                    if(currentUser.isAdmin()) {
                        user = userComboBox.getValue();
                    }
                    else {
                        user = currentUser;
                    }

                    ImageView imageView;
                    if(seat.getOwner() == null) {
                        seat.setOwner(user);
                        imageView = new ImageView(reversedSeatImage);
                        System.out.println("SOLD");
                    }
                    else {
                        seat.setOwner(null);
                        imageView = new ImageView(emptySeatImage);
                        System.out.println("RE");
                    }

                    button.setGraphic(imageView);
                });

                button.setOnAction(event -> {

                });

                gridPane.add(button, column, row);
            }
        }

        Button backButton = new Button("BACK");
        backButton.setOnAction(event -> {
            appManager.showFilmWindow();
        });

        HBox buttonBox = new HBox(backButton, userComboBox);
        buttonBox.setAlignment(Pos.CENTER);

         Text text = new Text("This page doesn't WORK");

        VBox vBox = new VBox(text, gridPane, buttonBox);
        Scene scene = new Scene(vBox);
        setScene(scene);
    }

}
