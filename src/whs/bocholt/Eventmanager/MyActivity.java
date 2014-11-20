package whs.bocholt.Eventmanager;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;

import java.lang.reflect.Array;
import java.util.Date;
import java.util.List;

import whs.bocholt.Eventmanager.objects.Event;
import whs.bocholt.Eventmanager.objects.User;
import whs.bocholt.Eventmanager.services.EventJsonService;

public class MyActivity extends ListActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    ListView list;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Long testID = (long) 123;
        List<Event> invations = EventJsonService.getAllInvitationsFromUser(testID);

        String[] name = new String[invations.size()];
        User[] admin = new User[invations.size()];
        Date[] date = new Date[invations.size()];

        for (int i = 0; i < invations.size(); i++) {
            Event event = invations.get(i);
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
                String event = "test";
                intent.putExtra(EXTRA_MESSAGE, event);
                startActivity(intent);
            }
        });

        //String[] events ={"Test1", "Test2", "Test 3", "Test4","Test5","Test6"};
        //ArrayAdapter<String> adapter= new ArrayAdapter<String>(getListView().getContext(), R.layout.list_single, events);
        //getListView().setAdapter(adapter);
    }

    public void openEvent(View view){
        Intent intent = new Intent(this, DisplayEventActivity.class);
        String event = "test";
        intent.putExtra(EXTRA_MESSAGE, event);
        startActivity(intent);
    }
}
