package whs.bocholt.Eventmanager.objects;

/**
 * Created by Maren on 06.11.14.
 */
public class User {

    public String username;
    public String password;
    public String mail;
    public boolean isAdmin;


    public User(String username, String password, String mail) {
        this.username = username;
        this.password = password;
        this.mail = mail;
    }

    public boolean isAdmin(){
        return isAdmin();
    }

    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }


}
