package whs.bocholt.Eventmanager;

/**
 * Created by Sebastian on 13.11.2014.
 */
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import whs.bocholt.Eventmanager.objects.User;

public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] eventname;
    private final User[] admin;
    public CustomList(Activity context,
                      String[] eventname, User[] admin) {
        super(context, R.layout.list_single, eventname);
        this.context = context;
        this.eventname = eventname;
        this.admin = admin;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.eventname);
        TextView myTextView = (TextView) rowView.findViewById(R.id.text);
        TextView txtAdmin = (TextView) rowView.findViewById(R.id.admin);
        txtTitle.setText(eventname[position]);
        txtAdmin.setText(admin[position].username);
        return rowView;
    }
}
