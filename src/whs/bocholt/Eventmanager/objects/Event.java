package whs.bocholt.Eventmanager.objects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Maren on 06.11.14.
 */
public class Event {

    public User admin;
    public long adminID;

    public long eventID;

    public String name;
    public String description;
    public Date date;
    public String location;

    public Event(long eventID, String name, String description, String date, String location) {
        this.eventID = eventID;
        this.name = name;
        this.description = description;

        parseDate(date);

        this.location = location;
    }

    private void parseDate(String date) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-DD hh:mm");
            this.date = dateFormat.parse(date);
        } catch (ParseException e) {
            System.err.println("ParseException ");
        }

    }

    public void setAdminUser(User user) {
        this.admin = user;
        this.adminID = user.userID;
    }
}
