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

import whs.bocholt.Eventmanager.activity.ParentActivity;
import whs.bocholt.Eventmanager.objects.Event;
import whs.bocholt.Eventmanager.objects.User;
import whs.bocholt.Eventmanager.services.JSONConstants;
import whs.bocholt.Eventmanager.services.JsonService;

/**
 * Created by Sebastian on 08.01.2015.
 */
public class NewEventActivity extends ParentActivity {

    private String eventPlace;
    private String eventDescription;
    private String eventTime;

    private EditText timeTextField;
    private EditText placeTextField;
    private EditText descriptionTextField;

    private String userID;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);

        userID = getIntent().getStringExtra("userID");


        timeTextField = (EditText) findViewById(R.id.timeTextField);
        placeTextField = (EditText) findViewById(R.id.placeTextField);
        descriptionTextField = (EditText) findViewById(R.id.descriptionTextField);
        Button createEventButton = (Button) findViewById(R.id.createEventButton);

        createEventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                eventPlace = placeTextField != null ? String.valueOf(placeTextField.getText()) : "";
                eventDescription = descriptionTextField != null ? String.valueOf(descriptionTextField.getText()) : "";
                eventTime = timeTextField != null ? String.valueOf(timeTextField.getText()) : "";

                if (eventPlace == null || eventPlace.equals("")) {
                    showErrorToast("Bitte geben Sie einen Ort ein");
                }
                if (eventDescription == null || eventDescription.equals("")) {
                    showErrorToast("Bitte geben Sie einen Eventnamen ein");
                }
                if (eventTime == null || eventTime.equals("")) {
                    showErrorToast("Bitte geben Sie einen Eventzeitpunkt ein");
                }

                String url = JSONConstants.URL_NEW_EVENT.replaceAll("adminuserid", userID).replaceAll("date", eventTime).replaceAll("name", eventDescription).replaceAll("ort", eventPlace);
                new NewEventService().execute(url);


                // Zum Server senden mit admin ID :)
            }
        });

    }

    public void goToDetailView(Event event){
        Intent intent = new Intent(NewEventActivity.this, DisplayEventActivity.class);
        String eventID = event.eventID;
        intent.putExtra("EventID", eventID);
        intent.putExtra("UserID" , userID);
        startActivity(intent);
    }



    class NewEventService extends AsyncTask<String, Void, Void> {

        JsonService jsonService;
        Event event;

        NewEventService() {
            this.jsonService = new JsonService();
        }

        @Override
        protected Void doInBackground(String... params) {
            URL url = null;
            try {
                url = new URL(params[0]);
                JSONObject jsonObject = jsonService.readJSONObjectFromURL(url);

                if (!jsonService.hasError(jsonObject)) {
                    JSONObject dataObject = jsonObject.getJSONObject("data");

                    event = new Event(dataObject.getString("eid"), dataObject.getString("bezeichnung"), dataObject.getString("zeit"), dataObject.getString("ort"));

                    JSONObject adminObject = dataObject.getJSONObject("admin");
                    User eventAdmin = new User(adminObject.getString("id"), adminObject.getString("vorname") + " " + adminObject.getString("name"), adminObject.getString("mail"));
                    event.setAdminUser(eventAdmin);
                } else {
                    String errorMessage = jsonService.getErrorMessage(jsonObject);
                    showErrorToast(errorMessage);
                    System.err.println("There was an error during creating the event." +
                                    jsonService.getErrorMessage(jsonObject)
                    );
                }
            } catch (MalformedURLException e) {
                System.err.println("Cannot create URL " + params[0]);
            } catch (JSONException e) {
                System.err.println("cannot read JSON!");
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            goToDetailView(event);
        }
    }
}
