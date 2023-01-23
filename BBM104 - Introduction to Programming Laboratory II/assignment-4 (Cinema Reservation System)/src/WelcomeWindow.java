import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


/**
 * a class for template welcome window. They are different windows
 * for admin and for normal users. This class provides a template.
 */
public abstract class WelcomeWindow extends Window{

    public WelcomeWindow(AppManager appManager) {
        super(appManager);

        VBox vBox = new VBox(getTextParent(),
                getDropdownFormParent(),
                getSpecialButtonsParent(),
                getLogOutParent());

        vBox.setSpacing(10);
        vBox.setPadding(new Insets(30));
        vBox.setAlignment(Pos.CENTER);

        setScene(new Scene(vBox));
    }

    /**
     * The text type that seen in the above. That includes username, Admin or User , and whether is user a club member.
     * @return Parent that keeps texts.
     */
    private Parent getTextParent(){

        // Create User:
        User user = getAppManager().getCurrentUser();

        // Create clubMemberString - that show status
        String clubMemberString;
        if(user.isClubMember())
            clubMemberString = " - Club Member";
        else
            clubMemberString = "";

        // Create Tests:
        Text text = new Text("Welcome " + user.getUsername() + " ( " + getSpecialType() + clubMemberString +" )") ;
        Text descriptionText = new Text(getSpecialDescription());

        // VBOX:
        VBox vBox = new VBox(text, descriptionText);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    /**
     * creates Dropdown list that has films and 'OK' button.
     * @return a Parent that keeps dropdown list and button.
     */
    private Parent getDropdownFormParent() {
        // Dropdown List:
        ObservableList<Film> filmObservableList = getAppManager().getDataManager().getFilmObservableList();
        ComboBox<Film> filmComboBox = new ComboBox<>(filmObservableList);

        if (filmObservableList.size() != 0)
            filmComboBox.setValue(filmObservableList.get(0));


        // TODO Fix That:
        // filmComboBox.setValue(filmObservableList.get(0));
        filmComboBox.setMinWidth(400);

        // OK Button:
        Button okButton = new Button("OK");
        okButton.setOnAction(event -> {
            Film film = filmComboBox.getValue();
            if (film != null) {
                getAppManager().setCurrentFilm(film);
                getAppManager().showFilmWindow();
                // TODO CHANGE HERE
                System.out.println(film);
            }
        });

        // HBox:
        HBox hBox  = new HBox(filmComboBox, okButton);
        hBox.setSpacing(20);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    /**
     * 'Log out' button and it's layout.
     * @return a Parent that keep 'log out' button.
     */
    private Parent getLogOutParent(){

        // Log out button:
        Button logOutButton = new Button("LOG OUT");
        logOutButton.setOnAction(event -> {
            getAppManager().showLoginWindow();
        });

        HBox hBox = new HBox(logOutButton);
        hBox.setAlignment(Pos.CENTER);

        return hBox;
    }

    /**
     * to create a layout to keep special buttons,
     * @return A parent to keep buttons.
     */
    protected abstract Parent getSpecialButtonsParent();

    /**
     * Type of user. For example, Admin or User
     * @return String to write
     */
    protected abstract String getSpecialType();

    /**
     * Description to say what users can do.
     * @return a String of description.
     */
    protected abstract String getSpecialDescription();

}
