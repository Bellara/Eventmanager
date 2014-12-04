package whs.bocholt.Eventmanager.services;

/**
 * Created by Maren on 13.11.14.
 */
public class JSONConstants {

    public static final String URL_USER_LOGIN = "http://eventmanager.pythonanywhere.com/user/login?mail=mString&pw=password";

    public static final String JSON_RESULT = "result";
    public static final String JSON_DATA = "data";

    public static final String JSON_MESSAGE_STATUS_SUCCESS = "succes";
    public static final String JSON_MESSAGE_STATUS_ERROR = "error";

    public static final int INVITATION_STATUS_UNDECIDED = 0;
    public static final int INVITATION_STATUS_ACCEPT = 1;
    public static final int INVITATiON_STATUS_DECLINED = 2;
}
