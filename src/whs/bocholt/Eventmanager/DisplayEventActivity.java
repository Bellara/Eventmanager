package whs.bocholt.Eventmanager;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import whs.bocholt.Eventmanager.objects.Event;
import whs.bocholt.Eventmanager.services.EventJsonService;

/**
 * Created by Sebastian Nienhaus on 20.11.2014.
 */
public class DisplayEventActivity extends Activity{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_event);

        // Get the message from the intent
        Intent intent = getIntent();
        Long eventId = intent.getLongExtra("EventID", -1);

        Event event = EventJsonService.getDetailInformatoinByEventID(eventId);

        // Layout
        // LinearLayout linLayout = new LinearLayout(this);
        //ActionBar.LayoutParams linLayoutParam = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
        //ActionBar.LayoutParams lpView = new ActionBar.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);

        // Eventname
        //TextView name = new TextView(this);
        //name.setTextSize(20);
        //name.setText(event.name);

        // Veranstalter
        //TextView veranstalter = new TextView(this);
        //veranstalter.setTextSize(15);
        //veranstalter.setText("Veranstalter: " + event.admin.username);

        //Beschreibung
        // TextView beschreibung = new TextView(this);
        // beschreibung.setTextSize(15);
        //beschreibung.setText("Beschreibung: \n " + event.description);

        //Checkbox
        //CheckBox checkbox = new CheckBox(this);
        //checkbox.setText("kommt");

        // Set the text view as the activity layout
        //setContentView(linLayout, linLayoutParam);
        //linLayout.addView(name, lpView);
        //linLayout.addView(veranstalter, lpView);

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
}
