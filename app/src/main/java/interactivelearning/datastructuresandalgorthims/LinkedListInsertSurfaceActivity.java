package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created on 19/03/2015.
 */
public class LinkedListInsertSurfaceActivity extends Activity {

    //GLSurface to draw to for activity
    private GLSurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //get information passed to it via a bundle.
        Bundle bundle = this.getIntent().getExtras();
        String index = bundle.getString("index");
        String value = bundle.getString("value");

        //Assign GLSurfaceView a view
        myView = new LinkedListInsertSurfaceView(getApplicationContext(), index, value);
        setContentView(myView);
    }
}
