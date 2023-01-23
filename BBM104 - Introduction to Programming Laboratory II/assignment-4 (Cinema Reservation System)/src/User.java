import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class User {

    private String hashedPassword;
    private SimpleStringProperty username = new SimpleStringProperty(this, "username");
    private SimpleBooleanProperty isClubMember = new SimpleBooleanProperty(this, "isClubMember");
    private SimpleBooleanProperty isAdmin = new SimpleBooleanProperty(this, "isAdmin");

    public User(String username, String hashedPassword, boolean isClubMember, boolean isAdmin) {
        this.username.set(username);
        this.hashedPassword = hashedPassword;
        this.isClubMember.set(isClubMember);
        this.isAdmin.set(isAdmin);
    }

    public String getUsername() {
        return username.get();
    }

    public void setUsername(String username) {
        this.username.set(username);
    }

    public SimpleStringProperty usernameProperty() {
        return username;
    }

    public String getHashedPassword() {
        return hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public boolean isClubMember() {
        return isClubMember.get();
    }

    public void setClubMember(boolean clubMember) {
        isClubMember.set(clubMember);
    }

    public SimpleBooleanProperty isClubMemberProperty() {
        return isClubMember;
    }

    public boolean isAdmin() {
        return isAdmin.get();
    }

    public void setAdmin(boolean admin) {
        isAdmin.set(admin);
    }

    public SimpleBooleanProperty isAdminProperty() {
        return isAdmin;
    }

    @Override
    public String toString() {
        return username.get();
    }
}
