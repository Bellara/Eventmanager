package whs.bocholt.Eventmanager.json;


import android.util.JsonReader;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import whs.bocholt.Eventmanager.objects.User;

/**
 * Created by Maren on 06.11.14.
 */
public class MyJSONParser {

    private static final String JSON_RESULT = "result";
    private static final String JSON_DATA = "data";

    private static final String JSON_MESSAGE_STATUS_SUCCESS = "Succes";
    private static final String JSON_MESSAGE_STATUS_ERROR = "Error";


    /**
     * Gets the login-json-object
     *
     * {
         "result": {
             "status":"success",
             "message":""
         },

         "data":  {
             "id":"1234567",
             "name": "Breuersbrock",
             "vorname": "Henning",
             "mail": "test@test.de"
         }
     }
     *
     * @param mail
     * @param password
     */
    public User userLogin(String mail, String password){

        User user = null;

        try {
            //URL to get the JSON-Object
            URL url = new URL("http://server/user/login?mail=" + mail + "&pw=" + password);

            //Open inputstream
            InputStream inputStream = url.openStream();

            //init JSONReader
            JsonReader jsonReader = new JsonReader(new InputStreamReader(inputStream, "UTF-8"));

            jsonReader.beginObject();
            while(jsonReader.hasNext()){

                String name = jsonReader.nextName();
                //Read the JSON-Object within "result"
                if(JSON_RESULT.equals(name)){
                    String status = jsonReader.nextString();
                    if(JSON_MESSAGE_STATUS_ERROR.equals(status)){
                        //Errorhandling
                        //Fehler dem User anzeigen
                    }
                }
                //Read the JSON-Object within "data"
                else if(JSON_DATA.equals(name)){
                    Long userID = (long) 0;
                    String firstName = "";
                    String lastName = "";
                    String userMail = "";

                    String nextName = jsonReader.nextName();
                    switch(nextName){
                        case "id":
                            userID = jsonReader.nextLong();
                            break;
                        case "firstname":
                            firstName = jsonReader.nextString();
                            break;
                        case "lastname":
                            lastName = jsonReader.nextString();
                            break;
                        case "mail":
                            userMail = jsonReader.nextString();
                            break;
                    }

                    user = new User(firstName + lastName, password, userMail);
                }
            }
            jsonReader.endObject();
        } catch (MalformedURLException e) {
            System.err.println("Cannot open URL");
        } catch (IOException e) {
            System.err.println("Cannot open the stream of the url ");
        }
        return user;
    }
}
