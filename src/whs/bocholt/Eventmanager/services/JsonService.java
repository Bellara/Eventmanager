package whs.bocholt.Eventmanager.services;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URL;


/**
 * Created by Maren on 13.11.14.
 */
public class JsonService {

    public static final String JSON_STATUS = "status";
    public static final String JSON_MESSAGE = "message";

    /**
     * Reads the URL-String and returns an JSONObject from the whole JSON-Object
     * @param url
     * @return
     */

    public JSONObject readJSONObjectFromURL(URL url){
        try {
            InputStream inputStream = url.openStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            String jsonText = readAll(bufferedReader);
            JSONObject jsonObject = new JSONObject(jsonText);
            return jsonObject;
        } catch (UnsupportedEncodingException e) {
            System.err.println("There was an error with the encoding");
        } catch (IOException e) {
            System.err.println("Cannot open Stream from URL " + url.toString());
        } catch (JSONException e) {
            System.err.println("Cannot create JSONObjekt");
        }
        return null;
    }

    /**
     * read all the text from the given Reader and returns a string of it
     * @param reader
     * @return
     * @throws IOException
     */
    private String readAll(Reader reader) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        int current;

        while((current = reader.read()) != -1){
            stringBuilder.append((char) current);
        }

        return stringBuilder.toString();
    }


    /**
     * Checks whether the Data-object has an error
     * @param resultJsonObject
     * @return
     */
    public boolean hasError(JSONObject resultJsonObject){
        try {
            return resultJsonObject.getString(JSON_STATUS).equals(JSONConstants.JSON_MESSAGE_STATUS_ERROR);
        } catch (JSONException e) {
            System.err.println("Cannot find " + JSON_STATUS + " in the JSON-OBject: " + resultJsonObject.toString());
        }
        return false;
    }


    /**
     * Return the errormessage, if there is one.
     * @param resultJsonObject
     * @return
     */
    public String getErrorMessage(JSONObject resultJsonObject){
        if(hasError(resultJsonObject)){
            try {
                return resultJsonObject.getString(JSON_MESSAGE);
            } catch (JSONException e) {
                System.err.println("Cannot find " + JSON_MESSAGE + " in the JSON-OBject: " + resultJsonObject.toString());
            }
        }
        return null;
    }
}
