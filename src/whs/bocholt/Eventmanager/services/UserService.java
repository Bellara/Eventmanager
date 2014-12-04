package whs.bocholt.Eventmanager.services;

import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import whs.bocholt.Eventmanager.UserLoginAsyncTask;
import whs.bocholt.Eventmanager.objects.User;

/**
 * Created by Maren on 27.11.14.
 */
public class UserService extends AsyncTask<String, Void, User> {

    private JsonService jsonService;

    public User user;

    public UserService() {
        this.jsonService = new JsonService();
    }

    @Override
    protected User doInBackground(String... params) {
        URL url = null;
        try {
            url = new URL(params[0]);
            JSONObject jsonObject = jsonService.readJSONObjectFromURL(url);
            if (!jsonService.hasError(jsonObject)) {
                JSONObject dataObject = jsonObject.getJSONObject("data");
                user = new User(dataObject.getString("id"), dataObject.getString("vorname") + " " + dataObject.getString("name"), dataObject.getString("mail"));
            } else {
                String errorMessage = jsonService.getErrorMessage(jsonObject);
                Toast.makeText(null, errorMessage, Toast.LENGTH_LONG).show();

                System.err.println("There was an error during login in with: " + url.toString() + "\n" +
                                jsonService.getErrorMessage(jsonObject)
                );
            }
        } catch (JSONException e) {
            System.err.println(e);
        } catch (MalformedURLException e) {
            System.err.println("Cannot create URL " + params[0]);
        }
        return user;
    }

    @Override
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
    }
}
