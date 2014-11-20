package whs.bocholt.Eventmanager;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import whs.bocholt.Eventmanager.objects.Event;
import whs.bocholt.Eventmanager.services.EventJsonService;

import java.util.List;

import whs.bocholt.Eventmanager.objects.Event;

public class MyActivity extends ListActivity {

    //private EventJsonService eventJsonService;
    public ListView list;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        justATest();

        //Long testID = (long) 123;
        //List<Event> invations = eventJsonService.getAllInvitationsByUser(testID);

        //String[] event = {"Test1","Test2"};
        //String[] admin = {"Sebastian","Henning"};
        //String[] date = {"28.11.14","11.11.15"};

//        CustomLtAdapter(adapter);

        //String[] events ={"Test1", "Test2", "Test 3", "Test4","Test5","Test6"};
        //ArrayAdapter<Strist adapter = new
//                CustomList(MyActivity.this, event, admin, date);
//        list=getListView();
//        list.seing> adapter= new ArrayAdapter<String>(getListView().getContext(), R.layout.list_single, events);
        //getListView().setAdapter(adapter);
    }

    private void justATest(){
        EventJsonService eventJsonService = new EventJsonService();
        long userID = 1000;
        Event event = eventJsonService.getDetailInformatoinByEventID(userID);
        System.out.println("Event " + event.eventID);
    }
}
