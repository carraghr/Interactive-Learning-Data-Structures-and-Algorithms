package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created on 19/03/2015.
 */
public class ArrayBinarySearchSurfaceView extends GLSurfaceView{

    private ArrayBinarySearchRenderer arrayBinarySearchRenderer;
    private Context context;

    private String searchFor;
    private String[] values;
    private boolean start = true;
    private int middle;

    public ArrayBinarySearchSurfaceView(Context context, String searchFor, String[] values){
        //call constructor for view
        super(context);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        //set up variables.
        this.context = context;
        this.searchFor = searchFor;
        this.values = values;

        //get image names for input to be shown.
        String [] fileNames = InputControls.addImageNames(values);
        String searchForImage = InputControls.addImageName(this.searchFor);

        //set up renderer for surfaceView and set the renderer.
        arrayBinarySearchRenderer = new ArrayBinarySearchRenderer(context,values.length,fileNames,searchForImage);
        setRenderer(arrayBinarySearchRenderer);

        //continue to render at all times.
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        //Message to tell user to tap screen to start the diagram.
        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

    //Messages to be shown after diagrams process is finished.
    private void messages() {
        Toast message;
        message = Toast.makeText(context, "We find the middle value of the array "
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "If it's greater than " + middle + ", it checks the right "
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "If it's less than " + middle+ ", it checks the left"
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "and repeats, until it finds it or runs out of values."
                , Toast.LENGTH_LONG);
        message.show();
    }

    //Process for the diagram to do.
    private void startProcess(){

        try {
            //change start to false as diagram is in motion.
            start = false;

            //set high and low index of array.
            int low = 0;
            int high = values.length-1;

            while(high >= low) {

                //find middle value between high and low indexes.
                middle = (low + high) / 2;

                //render middle value highlight
                arrayBinarySearchRenderer.highLight(middle);
                Thread.sleep(500);

                //if the value and the middle value are the same process is completed.
                if(values[middle].compareTo(searchFor)==0) {
                    //Success case
                    messages();
                    Toast message = Toast.makeText(context, "They are the same, so we are done.", Toast.LENGTH_LONG);
                    message.show();
                    return;
                }

                //remove highlight as other move is been made. Render this.
                arrayBinarySearchRenderer.removeHighLight(middle);
                Thread.sleep(500);

                // find which way the value is from the middle
                if(values[middle].compareTo(searchFor) < 0){
                    //searchFor is greater than middle
                    low = middle + 1;
                }
                else if(values[middle].compareTo(searchFor) > 0 ) {
                    //searchFor is less than middle
                    high = middle - 1;
                }
            }

            //message to say array doesn't contain value been searched for.
            messages();
            Toast message = Toast.makeText(context, "Array doesn't contain "+searchFor, Toast.LENGTH_LONG);
            message.show();
        }catch (Exception e){//catch exception sleep may cause.
            System.err.println("Error in start process");
        }
    }

    //get on touch event to start diagram process.
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