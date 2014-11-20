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

import java.util.List;

import whs.bocholt.Eventmanager.objects.Event;

public class MyActivity extends ListActivity {
    public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";

    //private EventJsonService eventJsonService;
    ListView list;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Long testID = (long) 123;
        //List<Event> invations = eventJsonService.getAllInvitationsByUser(testID);

        String[] event = {"Test1","Test2"};
        String[] admin = {"Sebastian","Henning"};
        String[] date = {"28.11.14","11.11.15"};

        CustomList adapter = new
                CustomList(MyActivity.this, event, admin, date);
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
