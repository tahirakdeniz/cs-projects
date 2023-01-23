import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class AdminWelcomeWindow extends WelcomeWindow {

    public AdminWelcomeWindow(AppManager appManager) {
        super(appManager);
    }

    @Override
    protected Parent getSpecialButtonsParent() {
        Button addFilmButton = new Button("Add Film");
        addFilmButton.setOnAction(event -> {
            // TODO Write Here:
            System.out.println("SHOW ADD FLIM SCREEN");
            getAppManager().showAddFilmWindow();
        });

        Button removeFilmButton = new Button("Remove Film");
        removeFilmButton.setOnAction(event -> {
            // TODO Write Here:
            System.out.println("REMOVE");
            getAppManager().showRemoveFilmWindow();
        });

        Button editUsersButton = new Button("Edit Users");
        editUsersButton.setOnAction(event -> {
            // TODO Write Here:
            System.out.println("EDIT USERS");
            getAppManager().showEditUserWindow();
        });

        HBox hBox = new HBox(addFilmButton, removeFilmButton, editUsersButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        return hBox;
    }

    @Override
    protected String getSpecialType() {
        return "Admin";
    }

    @Override
    protected String getSpecialDescription() {
        return "You can select film below or do edits.";
    }
}
