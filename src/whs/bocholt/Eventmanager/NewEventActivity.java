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


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_event);


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
                    return;
                }
                if (eventDescription == null || eventDescription.equals("")) {
                    showErrorToast("Bitte geben Sie einen Eventnamen ein");
                    return;
                }
                if (eventTime == null || eventTime.equals("")) {
                    showErrorToast("Bitte geben Sie einen Eventzeitpunkt ein");
                    return;
                }

                // Zum Server senden mit admin ID :)
            }
        });

    }
}
