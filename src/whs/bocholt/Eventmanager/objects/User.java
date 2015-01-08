package whs.bocholt.Eventmanager.objects;

import java.io.Serializable;

/**
 * Created by Maren on 06.11.14.
 */
public class User implements Serializable{

    public String userID;
    public String username;
    public String password;
    public String mail;
    public boolean isAdmin;


    public User(String userID,String username, String mail) {
        this.userID = userID;
        this.username = username;
        this.mail = mail;
    }

    public boolean checkPassword(String password){
        return this.password.equals(password);
    }

    public void setPassword(String password){
        this.password = password;
    }

    public boolean isAdmin(){
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin){
        this.isAdmin = isAdmin;
    }


}
