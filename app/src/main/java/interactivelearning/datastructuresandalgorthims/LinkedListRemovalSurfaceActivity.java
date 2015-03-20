package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created on 20/03/2015.
 */
public class LinkedListRemovalSurfaceActivity extends Activity{
    private GLSurfaceView myView;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getIntent().getExtras();
        String index = bundle.getString("index");

        myView = new LinkedListRemovalSurfaceView(getApplicationContext(), index);
        setContentView(myView);
    }
}
