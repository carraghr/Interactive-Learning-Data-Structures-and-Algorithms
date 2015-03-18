package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created on 18/03/2015.
 */
public class ArraySelectSortSurfaceView extends GLSurfaceView{

    private ArraySelectSortRenderer arraySelectSortRenderer;

    private Context context;
    private String [] values;
    private boolean start = true;

    ArraySelectSortSurfaceView(Context context,String[] values){
        super(context);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        this.context = context;
        this.values = values;

        String [] fileNames = InputControls.addImageNames(values);

        arraySelectSortRenderer = new ArraySelectSortRenderer(context,values.length,fileNames);
        setRenderer(arraySelectSortRenderer);

        //render all the time
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

    private void startProcess(){
        start = false;
        try {

        }catch (Exception e){}
    }

    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(start) {
                    startProcess();
                }
        }
        return true;
    }
}
