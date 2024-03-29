package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created on 20/03/2015.
 */
public class LinkedListRotationSurfaceActivity extends Activity {

    //GLSurface to draw to for activity
    private GLSurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //get information passed to it via a bundle.
        Bundle bundle = this.getIntent().getExtras();
        String strNumberOfRotates = bundle.getString("numberOfRotates");
        int numberOfRotates = Integer.valueOf(strNumberOfRotates);

        //Assign GLSurfaceView a view
        myView = new LinkedListRotationSurfaceView(getApplicationContext(), numberOfRotates);
        setContentView(myView);
    }
}
