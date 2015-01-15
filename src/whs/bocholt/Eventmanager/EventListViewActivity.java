package whs.bocholt.Eventmanager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import whs.bocholt.Eventmanager.objects.Event;
import whs.bocholt.Eventmanager.objects.Invitation;
import whs.bocholt.Eventmanager.objects.User;
import whs.bocholt.Eventmanager.services.JSONConstants;
import whs.bocholt.Eventmanager.services.JsonService;

public class EventListViewActivity extends ListActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    ListView list;

    public User user;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String userID = getIntent().getStringExtra("user");

        new EventService(userID).execute(JSONConstants.URL_GET_INVITATIONS + userID);
    }

    protected void showErrorToast(String errorMessage){
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

    public void showInvitations(final List<Invitation> invitations){
        String[] name = new String[invitations.size()];
        User[] admin = new User[invitations.size()];
        Date[] date = new Date[invitations.size()];

        for (int i = 0; i < invitations.size(); i++) {
            Invitation invitation = invitations.get(i);
            Event event = invitation.event;
            name[i]=event.name;
            admin[i]=event.admin;
            date[i] = event.date;
        }

        CustomList adapter = new CustomList(EventListViewActivity.this, name, admin);

        list=getListView();
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(EventListViewActivity.this, DisplayEventActivity.class);
                String eventID = invitations.get(position).event.eventID;
                intent.putExtra("EventID", eventID);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_new:
                openNew();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void openNew(){
        Intent intent = new Intent(this, NewEventActivity.class);
        startActivity(intent);
    }

    public void openEvent(View view){
        Intent intent = new Intent(this, DisplayEventActivity.class);
        String event = "test";
        intent.putExtra(EXTRA_MESSAGE, event);
        startActivity(intent);
    }

    class EventService extends AsyncTask<String, Void, List<Invitation>> {

        private JsonService jsonService;

        public String userID;
        public List<Invitation> invitations = new ArrayList<Invitation>();

        EventService(String userID) {
            this.jsonService = new JsonService();
            this.userID = userID;
        }

        @Override
        protected List<Invitation> doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                JSONObject jsonObject = jsonService.readJSONObjectFromURL(url);

                if (!jsonService.hasError(jsonObject)) {
                    JSONObject dataObject = jsonObject.getJSONObject("data");

                    initDataObjectToInvitationsList(dataObject);

                }
                else {
                    String errorMessage = jsonService.getErrorMessage(jsonObject);
                    showErrorToast(errorMessage);
                    System.err.println("There was an error during loading the invitations for user id " + userID + "\n" +
                        jsonService.getErrorMessage(jsonObject)
                    );
                }

            } catch (MalformedURLException e) {
                System.err.println("Cannot create URL " + params[0]);
            } catch (JSONException e) {
                System.err.println("Cannot get JSON");
            }

            if(invitations.size() == 0){
//                showErrorToast("Sie haben leider keine Einladungen zu einem Event");
            }
            return invitations;
        }

        @Override
        protected void onPostExecute(List<Invitation> invitations) {
            super.onPostExecute(invitations);
            showInvitations(invitations);
        }

        private void initDataObjectToInvitationsList(JSONObject dataObject) {

            try {
                JSONArray invitationsArray = dataObject.getJSONArray("invitations");

                for(int i = 0; i < invitationsArray.length(); i ++){
                    JSONObject invitationObject = invitationsArray.getJSONObject(i);

                    JSONObject eventObject = invitationObject.getJSONObject("event");
                    JSONObject userObject = eventObject.getJSONObject("admin");

                    User admin = new User(userObject.getString("id"), userObject.getString("vorname") + userObject.getString("nachname"), userObject.getString("mail"));

                    Event event = new Event(eventObject.getString("eid"), eventObject.getString("bezeichnung"),eventObject.getString("zeit"), eventObject.getString("ort"));
                    event.setAdminUser(admin);

                    Invitation invitation = new Invitation( event, invitationObject.getInt("status"));

                    invitations.add(invitation);
                }
                
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
