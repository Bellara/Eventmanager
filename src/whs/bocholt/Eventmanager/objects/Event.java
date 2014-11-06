package whs.bocholt.Eventmanager.objects;

import java.util.Date;

/**
 * Created by Maren on 06.11.14.
 */
public class Event {

    public User admin;
    public Long adminID;

    public String name;
    public String description;
    public Date date;
    public String location;

    public Event(String name, String description, Date date, String location) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.location = location;
    }
}
