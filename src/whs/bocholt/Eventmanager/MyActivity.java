package whs.bocholt.Eventmanager;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MyActivity extends ListActivity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String[] events ={"Test1", "Test2", "Party bei Tom", "otto hat Spass"};
        ArrayAdapter<String> adapter= new ArrayAdapter<String>(getListView().getContext(), android.R.layout.simple_list_item_1, events);
        getListView().setAdapter(adapter);
    }
}
