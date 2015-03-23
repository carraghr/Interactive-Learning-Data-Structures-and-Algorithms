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

        //set up variables
        this.context = context;
        this.searchFor = searchFor;
        this.values = values;
        String [] fileNames = InputControls.addImageNames(values);
        String searchForImage = InputControls.addImageName(this.searchFor);

        //set up renderer and apply it to surface view.
        arrayLinearSearchRenderer = new ArrayLinearSearchRenderer(context,values.length,fileNames,searchForImage);
        setRenderer(arrayLinearSearchRenderer);

        //render all the time
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        //message to user on how to start diagram.
        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

    //Message about diagram to be done at end
    private void messages() {
        Toast message;
        message = Toast.makeText(context, "We check each number and see if its equal to " + searchFor
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "When they are different, it moves to the next slot in the array."
                , Toast.LENGTH_LONG);
        message.show();
    }

    //process of diagram.
    private void startProcess(){

        try {
            start = false;
            for (int i = 0; i < values.length; i++) {
                //highlight where you are.
                arrayLinearSearchRenderer.highLight(i);
                Thread.sleep(500);
                //if the search for is the same as the vulue at i we are done searching for it
                if (searchFor.equals(values[i])) {
                    messages();
                    Toast message = Toast.makeText(context, "They are the same, so we are done.", Toast.LENGTH_LONG);
                    message.show();
                    return;
                }
                //else we move on.
                arrayLinearSearchRenderer.removeHighLight(i);
                arrayLinearSearchRenderer.moveSearchItemNext(i);
                Thread.sleep(500);
            }
            //message to display at end telling its not there
            messages();
            Toast message = Toast.makeText(context, "Array doesn't contain "+searchFor, Toast.LENGTH_LONG);
            message.show();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //on touch method to start diagram.
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
