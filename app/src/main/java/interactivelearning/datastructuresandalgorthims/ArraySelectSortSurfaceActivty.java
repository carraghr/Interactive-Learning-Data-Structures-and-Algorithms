package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created on 18/03/2015.
 */
public class ArraySelectSortSurfaceActivty extends Activity {

    private GLSurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        String [] values = bundle.getStringArray("values");

        myView = new ArraySelectSortSurfaceView(getApplicationContext(), values);
        setContentView(myView);
    }
}
