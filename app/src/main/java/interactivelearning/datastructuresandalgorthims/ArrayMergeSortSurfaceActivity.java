package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created on 19/03/2015.
 */
public class ArrayMergeSortSurfaceActivity extends Activity {
    private GLSurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        String searchFor = bundle.getString("value");
        String [] values = SearchValues.ARRAY_LINEAR_SEARCH;

        myView = new ArrayMergeSortSurfaceView(getApplicationContext(), searchFor, values);
        setContentView(myView);
    }
}
