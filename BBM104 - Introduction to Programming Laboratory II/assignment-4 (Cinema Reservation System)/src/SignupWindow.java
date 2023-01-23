import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * A class to show Signup Window.
 */
public class SignupWindow extends Window {

    public SignupWindow(AppManager appManager) {
        super(appManager);

        // Texts:
        Text descriptionTextFirst = new Text("Welcome to the HUCS Cinema Reservation System!");
        Text descriptionTextSecond = new Text("Fill the form below to create a new account.");
        Text descriptionTextThird = new Text("You can go to Log in page by clicking LOG IN Button.");

        // TextHBox:
        VBox textVBox = new VBox(descriptionTextFirst, descriptionTextSecond, descriptionTextThird);
        textVBox.setSpacing(3);
        textVBox.setAlignment(Pos.CENTER);
        textVBox.setPadding(new Insets(20));

        // Labels:
        Label usernameLabel = new Label("Username: ");
        Label passwordLabel = new Label("Password: ");
        Label passwordLabelSecond = new Label("Password: ");

        // Text and Password Fields:
        TextField usernameTextField = new TextField();
        PasswordField passwordField1 = new PasswordField();
        PasswordField passwordField2 = new PasswordField();

        // Target Text:
        Text targetText = new Text();
        HBox targetTextHBox = new HBox(targetText);
        targetTextHBox.setAlignment(Pos.CENTER);
        targetTextHBox.setPadding(new Insets(10, 0, 0, 0));

        // Buttons:
        Button showLogInButton = new Button("LOG IN");
        Button signUpButton = new Button("SIGN UP");

        showLogInButton.setOnAction(event -> appManager.showLoginWindow());

        signUpButton.setOnAction(event -> {

            try {
                String username = usernameTextField.getText();
                String password1 = passwordField1.getText();
                String password2 = passwordField2.getText();

                if(username.equals("") || password1.equals("") || password2.equals(""))
                    throw new NullFieldException();

                if (!password1.equals(password2))
                    throw new PasswordsAreDifferent();

                appManager.getUserManager().addUserBySignup(username, password1);

                // Clear Fields:
                usernameTextField.clear();
                passwordField1.clear();
                passwordField2.clear();

                targetText.setText("Successfully Signed Up");
            }
            catch (AppException e){
                appManager.playErrorSound();
                targetText.setText(e.getMessage());
            }
        });

        // Box for Sign up button:
        HBox signUpHBox = new HBox(signUpButton);
        signUpHBox.setAlignment(Pos.CENTER_RIGHT);

        // GridPane:
        GridPane gridPane = new GridPane();

        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        gridPane.addRow(0, usernameLabel, usernameTextField);
        gridPane.addRow(1, passwordLabel, passwordField1);
        gridPane.addRow(2, passwordLabelSecond, passwordField2);
        gridPane.addRow(3, showLogInButton, signUpHBox);

        // Root and Scene:
        VBox vBox = new VBox(textVBox, gridPane, targetTextHBox);
        vBox.setPadding(new Insets(30));

        setScene(new Scene(vBox));
    }

}
