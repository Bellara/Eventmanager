package whs.bocholt.Eventmanager.services;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import whs.bocholt.Eventmanager.objects.User;

/**
 * Created by Maren on 20.11.14.
 */
public class UserJsonService extends JsonService{

    private static final String LOGIN_USER = "http://eventmanager.pythonanywhere.com/user/login?mail=mString&pw=password";

    /**
     * Logs the User in with the given mail and password. Moreover this method checks whether
     * the user already exists in the Database. In the end he returns the user-object.
     * @param mail
     * @param password
     * @return
     */
    public User loginUser(String mail, String password){
        User user = null;
        String urlString = LOGIN_USER.replaceAll("mString", mail).replaceAll("password", password);
        try{
            URL url = new URL(urlString);
            JSONObject jsonObject = readJSONObjectFromURL(url);
            if(!hasError(jsonObject)){
                JSONObject dataObject = jsonObject.getJSONObject("data");
                user = new User(dataObject.getString("id"), dataObject.getString("vorname") + " " + dataObject.getString("name"), dataObject.getString("mail"));
            }
        } catch (JSONException e) {
            System.err.println(e);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return user;
    }

}
