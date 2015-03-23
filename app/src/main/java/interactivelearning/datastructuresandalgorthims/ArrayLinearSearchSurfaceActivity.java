package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created on 14/03/2015.
 */
public class ArrayLinearSearchSurfaceActivity extends Activity{

    //GLSurface to draw to for activity
    private GLSurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        //get information passed to it via a bundle.
        Bundle bundle = this.getIntent().getExtras();
        String searchFor = bundle.getString("value");

        //get array of numbers to be used.
        String [] values = SearchValues.ARRAY_LINEAR_SEARCH;

        //create and set view to be used.
        myView = new ArrayLinearSearchSurfaceView(getApplicationContext(), searchFor, values);
        setContentView(myView);
    }
}