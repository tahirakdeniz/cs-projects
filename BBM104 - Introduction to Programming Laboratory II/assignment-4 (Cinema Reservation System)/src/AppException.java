import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class AppException extends Exception {
    public AppException(String message) {
        super(message);

        Media media = new Media(new File("assets/effects/error.mp3").toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
    }
//TODO ADD MESSAGES
}

class NullFieldException extends AppException {

    public NullFieldException() {
        super("ERROR: NULL FIELD EXCEPTION");
    }
}

class NoSuchACredentialException extends AppException {
    public NoSuchACredentialException() {
        super("ERROR: No Such A Credential Exception");
    }
}

class UsernameNotFoundException extends AppException {
    //TODO DELETE IF YOU DONT USE

    public UsernameNotFoundException() {
        super("UsernameNotFoundException");
    }
}

class PasswordsAreDifferent extends AppException {
    public PasswordsAreDifferent() {
        super("Passwords Are Different");
    }
}

class UsernameAlreadyExist extends AppException{
    public UsernameAlreadyExist() {
        super("Username Already Exist");
    }
}

class NumberConvertingException extends AppException{
    public NumberConvertingException() {
        super("ERROR: ENTER A NUMBER");
    }
}

class FilmNameAlreadyExist extends AppException {
    public FilmNameAlreadyExist() {
        super("ERROR: FILM ALREADY EXIST");
    }
}

