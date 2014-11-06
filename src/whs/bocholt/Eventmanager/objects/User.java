package whs.bocholt.Eventmanager.objects;

/**
 * Created by Maren on 06.11.14.
 */
public class User {

    public String username;
    public String password;
    public String mail;
    public boolean isAdmin;


    public User(String username, String password, String mail, boolean isAdmin) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.isAdmin = isAdmin;
    }
}
