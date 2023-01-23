import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * A class to show Edit User Window.
 */
public class EditUserWindow extends Window {
    public EditUserWindow(AppManager appManager) {
        super(appManager);

        // TableView:
        TableColumn<User,String> usernameCol = new TableColumn<>("Username");
        usernameCol.setCellValueFactory(userStringCellDataFeatures -> userStringCellDataFeatures.getValue().usernameProperty());
        usernameCol.setMinWidth(150);

        TableColumn<User, Boolean> clubMemberCol = new TableColumn<>("Club Member");
        clubMemberCol.setCellValueFactory(param -> param.getValue().isClubMemberProperty());
        clubMemberCol.setMinWidth(150);

        TableColumn<User,Boolean> isAdminCol = new TableColumn<>("Admin Name");
        isAdminCol.setCellValueFactory(userBooleanCellDataFeatures -> userBooleanCellDataFeatures.getValue().isAdminProperty());
        isAdminCol.setMinWidth(150);

        ObservableList<User> userObservableList = appManager.getUserManager().getUserObservableList();
        userObservableList.remove(appManager.getCurrentUser());

        TableView<User> userTableView = new TableView<>();
        userTableView.setItems(userObservableList);

        userTableView.getColumns().setAll(usernameCol,clubMemberCol, isAdminCol);
        // TODO Default setting.

        // Button:
        Button backButton = new Button("< Back");
        backButton.setOnAction(event -> {
            appManager.showWelcomeWindow();
        });

        Button changeClubMemberButton = new Button("Promote/Demote Club Member");
        changeClubMemberButton.setOnAction(event -> {
            User user = userTableView.getSelectionModel().getSelectedItem();

            if(user == null)
                return;

            user.setClubMember(!user.isClubMember());
        });

        Button changeAdminButton = new Button("Promote/Demote Admin");
        changeAdminButton.setOnAction(event -> {
            User user = userTableView.getSelectionModel().getSelectedItem();

            if(user == null)
                return;

            user.setAdmin(!user.isAdmin());
        });

        HBox buttonHBox = new HBox(backButton, changeClubMemberButton, changeAdminButton);
        buttonHBox.setAlignment(Pos.CENTER);
        buttonHBox.setSpacing(10);

        // Root and Scene:
        VBox vBox = new VBox(userTableView, buttonHBox);
        vBox.setPadding(new Insets(30));
        vBox.setSpacing(20);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        setScene(scene);
    }
}
