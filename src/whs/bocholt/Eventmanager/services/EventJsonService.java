package whs.bocholt.Eventmanager.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import whs.bocholt.Eventmanager.objects.Event;
import whs.bocholt.Eventmanager.objects.User;

/**
 * Created by Maren on 13.11.14.
 */
public class EventJsonService extends JsonService{

    public ArrayList<Event> events;

    public static final String URL_GET_INVITATIONS = "http://server/events/getInvitations?uid=";
    public static final String URL_GET_DETAIL_EVENT_INFORMATION = "http://server/events/getById?id=";


    /**
     * Returns all Invitations a user have as a list.
     * @param userID
     * @return
     */
    public ArrayList<Event> getAllInvitationsFromUser(long userID){
        events = new ArrayList<>();
        try {
            /**
            URL myURL = new URL(URL_GET_INVITATIONS + userID);

            JSONObject jsonObject = readJSONObjectFromURL(myURL);
               **/

            JSONObject jsonObject = new JSONObject(JSONTest.GET_ALL_INVITATIONS.toString());

            if(hasError(jsonObject.getJSONObject("result"))){
                System.err.println("FEHER! Bei den JSON-Dateien für getAllInvitationsFromUser");
                return null;
            }

            JSONObject dataObject = jsonObject.getJSONObject("data");
            JSONArray invitations = dataObject.getJSONArray("invitations");

            for(int i = 0; i < invitations.length(); i++){
                JSONObject invitation = invitations.getJSONObject(i);
                JSONObject eventJson = invitation.getJSONObject("event");

                //getEvent
                Event event = new Event(eventJson.getLong("eid"), eventJson.getString("bezeichnung"),
                                            "", eventJson.getString("zeit"), eventJson.getString("ort"));

                //getAdminuser
                JSONObject adminObject = eventJson.getJSONObject("admin");
                User user = new User(adminObject.getLong("id"), adminObject.getString("vorname") + adminObject.getString("name"),
                                                            adminObject.getString("mail"));

                event.setAdminUser(user);
                events.add(event);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return events;
    }

    /**
     *  Returns detailInformation of an Event getting by the id.
     * @param eventID
     * @return
     */
    public Event getDetailInformatoinByEventID(Long eventID){

        Event event = null;
        /**
        try {
            URL myURL = new URL(URL_GET_DETAIL_EVENT_INFORMATION + eventID);
            JSONObject jsonObject = readJSONObjectFromURL(myURL);
        } catch (MalformedURLException e) {
            System.err.println("Fehler beim Erstellen der URL " + URL_GET_DETAIL_EVENT_INFORMATION + eventID + "\n" + e);
        }**/
        try {
            JSONObject jsonObject = new JSONObject(JSONTest.GET_DETAIL_EVENT_INFORMATION.toString());
            if(hasError(jsonObject.getJSONObject("result"))){
                return null;
            }

            //fill Event with JSON-Information
            JSONObject dataObject = jsonObject.getJSONObject("data");
            event = new Event(dataObject.getLong("eid"), dataObject.getString("bezeichnung"), "", dataObject.getString("zeit"), dataObject.getString("ort"));

            //fill eventadmin
            JSONObject adminObject = dataObject.getJSONObject("admin");
            User user = new User(adminObject.getLong("id"), adminObject.getString("vorname") + adminObject.getString("name"),
                    adminObject.getString("mail"));

            event.setAdminUser(user);

        } catch (JSONException e) {
            System.err.println("Konnte das JSON-Objekt nicht erzeugen: " + JSONTest.GET_DETAIL_EVENT_INFORMATION + e);
        }
        return event;
    }
}
