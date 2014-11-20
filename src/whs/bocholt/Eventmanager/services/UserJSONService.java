package whs.bocholt.Eventmanager.services;

import org.json.JSONException;
import org.json.JSONObject;

import whs.bocholt.Eventmanager.objects.User;

/**
 * Created by Maren on 20.11.14.
 */
public class UserJsonService extends JsonService{

    private static final String LOGIN_USER = "http://server/user/login?mail=mString&pw=password";

    /**
     * Logs the User in with the given mail and password. Moreover this method checks whether
     * the user already exists in the Database. In the end he returns the user-object.
     * @param mail
     * @param password
     * @return
     */
    public static User loginUser(String mail, String password){
        String urLStirng = LOGIN_USER.replaceAll("mString", mail).replaceAll("password", password);
        User user = null;

        /**
         try {
            String urLStirng = LOGIN_USER.replaceAll("mString", mail).replaceAll("password", password);
            URL url = new URL(urlString);
            JSONObject jsonObject = readJSONObjectFromURL(url);
         } catch (MalformedURLException e) {
            System.err.println(e);
         }*/

        try {
            JSONObject jsonObject = new JSONObject(JSONTest.LOGIN_USER.toString());
            if(!hasError(jsonObject)){
                JSONObject dataObject = jsonObject.getJSONObject("data");
                user = new User(dataObject.getLong("id"), dataObject.getString("vorname") + " " + dataObject.getString("name"), dataObject.getString("mail"));
            }
        } catch (JSONException e) {
            System.err.println(e);
        }
        return user;
    }

}
