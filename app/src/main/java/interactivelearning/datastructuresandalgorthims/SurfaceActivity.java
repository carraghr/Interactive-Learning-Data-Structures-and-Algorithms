package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created by Richard Carragher on 06/03/2015.
 */
public class SurfaceActivity extends Activity {

    private GLSurfaceView myView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        myView = new MySurfaceView(getApplicationContext());
        setContentView(myView);
    }
}
