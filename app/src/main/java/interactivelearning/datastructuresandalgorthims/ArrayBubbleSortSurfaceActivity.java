package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created on 19/03/2015.
 */
public class ArrayBubbleSortSurfaceActivity extends Activity {

    //GLSurface to draw to for activity
    private GLSurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //get information passed to it via a bundle.
        Bundle bundle = this.getIntent().getExtras();
        String [] values = bundle.getStringArray("values");

        //Assign GLSurfaceView a binary search view
        myView = new ArrayBubbleSortSurfaceView(getApplicationContext(), values);
        setContentView(myView);
    }
}