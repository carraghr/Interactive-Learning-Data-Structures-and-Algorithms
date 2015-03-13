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

        Bundle bundle = this.getIntent().getExtras();
        String type = bundle.getString("type");
        int num =Integer.parseInt(bundle.getString("number_of_Slots"));
        String [] values;
        if(type.equals("Declaration")){
            values=null;
        }else{
            values = bundle.getStringArray("values");
        }

        myView = new MySurfaceView(getApplicationContext(),type,num,values);
        setContentView(myView);
    }
}