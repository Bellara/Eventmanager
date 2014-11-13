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
import java.util.ArrayList;

import whs.bocholt.Eventmanager.objects.Event;
import whs.bocholt.Eventmanager.objects.User;

/**
 * Created by Maren on 13.11.14.
 */
public class EventJsonService extends JsonService{

    public ArrayList<Event> events;

    public static final String URL_GET_INVITATIONS = "http://server/events/getInvitations?uid=";



    public ArrayList<Event> getAllInvitationsFromUser(long userID){
        events = new ArrayList<>();
        try {
            /**
            URL myURL = new URL(URL_GET_INVITATIONS + userID);

            JSONObject jsonObject = readJSONObjectFromURL(myURL);
               **/

            //File test_invitations = new File("invitations");
            //InputStream inputStream = new FileInputStream(test_invitations);

            //BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder("{\n" +
                    "    \"result\": {\n" +
                    "        \"status\":\"success\",\n" +
                    "        \"message\":\"\"\n" +
                    "    },\n" +
                    "\n" +
                    "    \"data\": {\n" +
                    "        \"invitations\": [\n" +
                    "            {\n" +
                    "                \"event\": {\n" +
                    "                    \"eid\":\"123456\",\n" +
                    "                    \"bezeichnung\":\"asdfasdf\",\n" +
                    "                    \"ort\":\"sfasdf\",\n" +
                    "                    \"zeit\":\"2014-15-11 20:00\",\n" +
                    "                    \"admin\": {\n" +
                    "                        \"id\":\"1234567\",\n" +
                    "                        \"name\": \"Breuersbrock\",\n" +
                    "                        \"vorname\": \"Henning\",\n" +
                    "                        \"mail\": \"test@test.de\",\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            \"status\": \"0\" / \"1\" / \"2\"  (0=nicht entschieden, 1=komme, 2=komme nicht)\n" +
                    "            },\n" +
                    "            {\n" +
                    "                \"event\": {\n" +
                    "                    \"eid\":\"123456\",\n" +
                    "                    \"bezeichnung\":\"asdfasdf\",\n" +
                    "                    \"ort\":\"sfasdf\",\n" +
                    "                    \"zeit\":\"2014-15-11 20:00\",\n" +
                    "                    \"admin\": {\n" +
                    "                        \"id\":\"1234567\",\n" +
                    "                        \"name\": \"Breuersbrock\",\n" +
                    "                        \"vorname\": \"Henning\",\n" +
                    "                        \"mail\": \"test@test.de\",\n" +
                    "                    }\n" +
                    "                }\n" +
                    "            \"status\": \"0\" / \"1\" / \"2\"  (0=nicht entschieden, 1=komme, 2=komme nicht)\n" +
                    "            }\n" +
                    "\n" +
                    "        ]\n" +
                    "    }\n" +
                    "}");
            //while(bufferedReader.readLine() != null){
            //    stringBuilder.append(bufferedReader.readLine());
            //}

            JSONObject jsonObject = new JSONObject(stringBuilder.toString());


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
}
