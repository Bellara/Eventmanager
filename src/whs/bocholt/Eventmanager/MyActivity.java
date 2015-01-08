package whs.bocholt.Eventmanager;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.Date;
import java.util.List;

import whs.bocholt.Eventmanager.objects.Event;
import whs.bocholt.Eventmanager.objects.Invitation;
import whs.bocholt.Eventmanager.objects.User;
import whs.bocholt.Eventmanager.services.EventJsonService;

public class MyActivity extends ListActivity {
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

        //justATest();

        Long testID = (long) 123;
        final List<Invitation> invations = EventJsonService.getAllInvitationsFromUser(testID);

        String[] name = new String[invations.size()];
        User[] admin = new User[invations.size()];
        Date[] date = new Date[invations.size()];

        for (int i = 0; i < invations.size(); i++) {
            Invitation invitation = invations.get(i);
            Event event = invitation.event;
            name[i]=event.name;
            admin[i]=event.admin;
        }

        CustomList adapter = new CustomList(MyActivity.this, name, admin);

        list=getListView();
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MyActivity.this, DisplayEventActivity.class);
                Long event = invations.get(position).event.eventID;
                intent.putExtra("EventID", event);
                startActivity(intent);
            }
        });
    }

    public void openEvent(View view){
        Intent intent = new Intent(this, DisplayEventActivity.class);
        String event = "test";
        intent.putExtra(EXTRA_MESSAGE, event);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
