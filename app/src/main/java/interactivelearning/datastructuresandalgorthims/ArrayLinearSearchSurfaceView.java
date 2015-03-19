package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;


/**
 * Created on 14/03/2015.
 */
public class ArrayLinearSearchSurfaceView extends GLSurfaceView{

    private ArrayLinearSearchRenderer arrayLinearSearchRenderer;
    Context context;

    String searchFor;
    String[] values;
    boolean start = true;

    public ArrayLinearSearchSurfaceView(Context context, String searchFor, String[] values){

        super(context);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        this.context = context;
        this.searchFor = searchFor;
        this.values = values;
        String [] fileNames = InputControls.addImageNames(values);
        String searchForImage = InputControls.addImageName(this.searchFor);

        arrayLinearSearchRenderer = new ArrayLinearSearchRenderer(context,values.length,fileNames,searchForImage);
        setRenderer(arrayLinearSearchRenderer);

        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

    private void messages() {
        Toast message;
        message = Toast.makeText(context, "We check each number and see if its equal to " + searchFor
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "When they are different, it moves to the next slot in the array."
                , Toast.LENGTH_LONG);
        message.show();
    }

    private void startProcess(){

        try {
            start = false;
            for (int i = 0; i < values.length; i++) {
                //highlight where you are.
                arrayLinearSearchRenderer.highLight(i);
                Thread.sleep(500);
                if (searchFor.equals(values[i])) {
                    messages();
                    Toast message = Toast.makeText(context, "They are the same, so we are done.", Toast.LENGTH_LONG);
                    message.show();
                    return;
                }
                arrayLinearSearchRenderer.removeHighLight(i);
                arrayLinearSearchRenderer.moveSearchItemNext(i);
                Thread.sleep(500);
            }
            messages();
            Toast message = Toast.makeText(context, "Array doesn't contain "+searchFor, Toast.LENGTH_LONG);
            message.show();
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
