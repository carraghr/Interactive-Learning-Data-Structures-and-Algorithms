package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created on 14/03/2015.
 */
public class ArrayLinearSearchSurfaceActivity extends Activity{

    private GLSurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        String searchFor = bundle.getString("value");
        String [] values = SearchValues.ARRAY_LINEAR_SEARCH;

        myView = new ArrayLinearSearchSurfaceView(getApplicationContext(), searchFor, values);
        setContentView(myView);
    }
}
