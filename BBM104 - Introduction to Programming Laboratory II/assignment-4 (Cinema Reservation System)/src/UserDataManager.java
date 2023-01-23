import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * A class to store and manage User instances. It stores User's in a map.
 * There are some methods to get, add and remove user, and also to log in
 * and sign up.
 */
public class UserDataManager {

    private final Map<String, User> userMap = new HashMap<>();

    /**
     * Add user to collection.
     * @param user User to add
     * @throws AppException if user already exist throws exception.
     */
    public void addUser(User user) throws AppException {
        if(userMap.containsKey(user.getUsername()))
            throw  new UsernameAlreadyExist();

        userMap.put(user.getUsername(), user);
    }

    public void removeUser(User user) {
        // TODO RETURN AGAIN UNUSED

        userMap.remove(user.getUsername());
    }

    public User getUser(String username) {
        // TODO TURN AGAIN UNUSED

        return userMap.get(username);
    }

    public User getUserByLogin(String username, String password) throws AppException{
        User user = userMap.get(username);

        if (user == null)
            throw new NoSuchACredentialException();

        // Check if password is true:
        if (!hashPassword(password).equals(user.getHashedPassword()))
            throw new NoSuchACredentialException();

        return user;

    }

    public void addUserBySignup(String username, String password) throws AppException {
        User user = new User(username, hashPassword(password), false, false);
        addUser(user);
    }

    public ObservableList<User> getUserObservableList() {
        return FXCollections.observableArrayList(userMap.values());
    }

    /**
     * Helper method to hash password. Returns Base64 encoded version of MD5 hashed version of the given password;
     * @param password Password to be hashed.
     * @return Base64 encoded version of MD5 hashed version of password;
     */
    private static String hashPassword(String password ) {
        byte [ ] bytesOfPassword = password.getBytes (StandardCharsets.UTF_8) ;
        byte [ ] md5Digest = new byte[0] ;
        try {
            md5Digest = MessageDigest.getInstance ( "MD5" ).digest ( bytesOfPassword ) ;
        } catch ( NoSuchAlgorithmException e ) {
            return null ;
        }
        return Base64.getEncoder( ).encodeToString(md5Digest) ;
    }
}



//TODO PASSWORD ISN'T EMPTY

