package whs.bocholt.Eventmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import whs.bocholt.Eventmanager.objects.User;
import whs.bocholt.Eventmanager.services.JSONConstants;
import whs.bocholt.Eventmanager.services.JsonService;

/**
 * Created by Maren on 27.11.14.
 */
public class UserLoginAsyncTask extends Activity {

    private String user_email;
    private String user_password;

    private EditText emailTextField;
    private EditText passwordTextField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_login);

        emailTextField = (EditText) findViewById(R.id.emailTextField);
        passwordTextField = (EditText) findViewById(R.id.passwordTextField);

        Button loginButton = (Button) findViewById(R.id.loginButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user_email = emailTextField != null ? String.valueOf(emailTextField.getText()) : "";
                user_password = passwordTextField != null ? String.valueOf(passwordTextField.getText()) : "";

                if (user_email == null || user_email.equals("")) {
                    showErrorToast("Bitte geben Sie eine Email-Adresse ein");
                    return;
                }
                if (user_password == null || user_password.equals("")) {
                    showErrorToast("Bitte geben Sie ein Passwort ein");
                    return;
                }

                new UserService().execute(JSONConstants.URL_USER_LOGIN.replaceAll("mString", user_email).replaceAll("password", user_password));
            }
        });
    }

    public void showErrorToast(String errorMessage){
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG);
    }

    public void openListView(User user){
        Intent intent = new Intent(this, MyActivity.class);
        intent.putExtra("user", user.userID);
        startActivity(intent);
    }

    class UserService extends AsyncTask<String, Void, User> {

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
            openListView(user);
        }
    }
}
