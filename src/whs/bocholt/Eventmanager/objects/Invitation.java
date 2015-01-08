package whs.bocholt.Eventmanager.objects;

import java.io.Serializable;

/**
 * Created by Maren on 06.11.14.
 */
public class Invitation implements Serializable{

    public Event event;
    public int status;

    public Invitation(Event event, int status) {
        this.event = event;
        this.status = status;
    }

    public void setStatus(int status){
        this.status = status;
    }
}
