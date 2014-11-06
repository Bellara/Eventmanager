package whs.bocholt.Eventmanager.objects;

/**
 * Created by Maren on 06.11.14.
 */
public class Invitation {

    public Event event;
    public int status;

    public Invitation(Event event, int status) {
        this.event = event;
        this.status = status;
    }

    public void setSTatus(int status){
        this.status = status;
    }
}
