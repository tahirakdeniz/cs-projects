import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.util.Timer;
import java.util.TimerTask;


/**
 * A class to start login window. Login window has forms for username
 * and password, and buttons to log in and to show signup window.
 */
public class LoginWindow extends Window{

    public LoginWindow(AppManager appManager) {
        super(appManager);

        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(30));

        borderPane.setTop(getTextParent());
        borderPane.setCenter(getFormParent());

        setScene(new Scene(borderPane));
    }

    /**
     * A private helper method for text part of window.
     * @return a parent that includes needed and organized texts;
     */
    private Parent getTextParent(){
        // Texts:
        Text text = new Text("Welcome to the HUCS Cinema Reservation System!");
        Text text1 = new Text("Please enter your credentials below and click LOGIN.");
        Text text2 = new Text("You can create a new account by clicking SIGN UP button.");

        // Vbox:
        VBox vBox = new VBox(text, text1, text2);
        vBox.setPadding(new Insets(0, 0, 25, 0));
        vBox.setSpacing(3);
        vBox.setAlignment(Pos.CENTER);

        return vBox;
    }

    /**
     * a private helper method for form part of window. That have labels, buttons and fields.
     * @return a Parent that contains needed Nodes.
     */
    private Parent getFormParent() {

        // For ErrorText:
        Text errorText = new Text();

        // For username:
        Label usernameLabel = new Label("Username:");
        TextField usernameTextField = new TextField();

        // For password:
        Label passwordLabel = new Label("Password:");
        PasswordField passwordField = new PasswordField();

        // loginButton:
        Button loginButton = new Button("LOGIN");

        loginButton.setOnAction(event -> {
            String username = usernameTextField.getText();
            String hashedPassword = passwordField.getText();


            User user;

            try {
                if(username.equals("") || hashedPassword.equals(""))
                    throw new NullFieldException();

                user = getAppManager().getUserManager().getUserByLogin(username, hashedPassword);

                getAppManager().setCurrentUser(user);
                getAppManager().showWelcomeWindow();

                usernameTextField.clear();
                passwordField.clear();

            } catch (AppException e) {

                // Increase wrong answer number :
                getAppManager().setWrongAnswerNumber(getAppManager().getWrongAnswerNumber() + 1);

                // Check wrong answer number:
                int blockTime = getAppManager().getPropertiesReader().getBlockTime();
                if(getAppManager().getWrongAnswerNumber() >= getAppManager().getPropertiesReader().getMaximumErrorWithoutGettingBlocked()) {
                    errorText.setText("ERROR: Please wait "+ (blockTime) + " seconds to make a new operation!");

                    // Timer:
                    Timer timer = new Timer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        int seconds = blockTime;
                        public void run() {
                            if(seconds > 0) {
                                seconds--;
                            }
                            else{
                                timer.cancel();
                                errorText.setText("");
                                getAppManager().setWrongAnswerNumber(0);
                            }
                        }
                    }, 1000,1000);
                }
                else {
                    // Error Message:
                    errorText.setText(e.getMessage());
                    getAppManager().playErrorSound();

                }
            }
        });

        // showSignupWindowButton:
        Button showSignupWindowButton = new Button("SIGNUP");
        showSignupWindowButton.setOnAction(event -> {

            // Clear Fields:
            usernameTextField.clear();
            passwordField.clear();

            getAppManager().showSignupWindow();
        });


        // HBox to alignment showSignupWindowButton:
        HBox signupButtonHBox = new HBox(showSignupWindowButton);
        signupButtonHBox.setAlignment(Pos.BASELINE_RIGHT);

        // HBox to alignment errorText
        HBox errorTextHBox = new HBox(errorText);
        errorTextHBox.setAlignment(Pos.CENTER);
        errorTextHBox.setPadding(new Insets(10, 0, 0, 0));

        // GridPane:
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        gridPane.setHgap(15);
        gridPane.setVgap(15);

        // Add items to gridPane:
        gridPane.addRow(0, usernameLabel, usernameTextField);
        gridPane.addRow(1, passwordLabel, passwordField);
        gridPane.addRow(2, loginButton, signupButtonHBox);

        // Add TextHBox:
        VBox vBox = new VBox(gridPane, errorTextHBox);

        return vBox;
    }
}
