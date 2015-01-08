package whs.bocholt.Eventmanager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.content.Intent;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import whs.bocholt.Eventmanager.activity.ParentActivity;
import whs.bocholt.Eventmanager.objects.Event;
import whs.bocholt.Eventmanager.services.JSONConstants;
import whs.bocholt.Eventmanager.services.JsonService;

/**
 * Created by Sebastian Nienhaus on 20.11.2014.
 */
public class DisplayEventActivity extends ParentActivity{

    private String eventID;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_event);

        // Get the message from the intent
        Intent intent = getIntent();
        eventID = intent.getStringExtra("EventID");

        new LoadEventService().execute(JSONConstants.URL_GET_DETAIL_EVENT_INFORMATION + eventID);

    }

    private void showEventDetailInformation(Event event) {
        TextView txtName = (TextView)findViewById(R.id.name);
        TextView txtHost = (TextView) findViewById(R.id.host);
        TextView txtOrt = (TextView) findViewById(R.id.ort);
        TextView txtDescription = (TextView) findViewById(R.id.description);
        TextView txtDate = (TextView) findViewById(R.id.date);
        txtName.setText(event.name);
        txtHost.setText(event.admin.username);
        txtOrt.setText(event.location);
        String format = "yyy-MM-dd HH:mm";
        SimpleDateFormat simpleDate = new SimpleDateFormat(format, Locale.GERMAN);
        txtDate.setText(simpleDate.format(event.date));
        txtDescription.setText(event.description);
    }

    class LoadEventService extends AsyncTask<String, Void, Void>{

        JsonService jsonService;

        private Event event;

        LoadEventService() {
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

                    event = new Event(dataObject.getString("eid"), dataObject.getString("descriptionn"), dataObject.getString("datum"), dataObject.getString("ort"));

                    showEventDetailInformation(event);
                }

                else {
                    String errorMessage = jsonService.getErrorMessage(jsonObject);
                    showErrorToast(errorMessage);
                    System.err.println("There was an error during loading the event: " + eventID + "\n" +
                                    jsonService.getErrorMessage(jsonObject)
                    );
                }
            } catch (JSONException e) {
                System.err.println(e);
            } catch (MalformedURLException e) {
                System.err.println("Cannot create URL " + params[0]);
            }
            return null;
        }
    }


}
