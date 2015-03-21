package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

/**
 * Created on 06/03/2015.
 */
public class ArrayDeclarationSurfaceActivity extends Activity {

    //GLSurface to draw to for activity
    private GLSurfaceView myView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get information passed to it via a bundle.
        Bundle bundle = this.getIntent().getExtras();
        String type = bundle.getString("type");
        int num =Integer.parseInt(bundle.getString("number_of_Slots"));
        String [] values;

        //check to see if there are any values been passed by type of diagram user has entered
        if(type.equals("Declaration")){
            values=null;
        }else{
            values = bundle.getStringArray("values");
        }
        //create and set view to be used.
        myView = new ArrayDeclarationSurfaceView(getApplicationContext(),type,num,values);
        setContentView(myView);
    }
}