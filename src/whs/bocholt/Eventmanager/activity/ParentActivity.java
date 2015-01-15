package whs.bocholt.Eventmanager.activity;

import android.app.Activity;
import android.widget.Toast;

/**
 * Created by Maren on 08.01.15.
 */
public class ParentActivity extends Activity{



    protected void showErrorToast(String errorMessage){
        Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
    }

}
