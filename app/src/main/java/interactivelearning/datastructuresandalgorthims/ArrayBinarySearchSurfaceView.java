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
    Context context;

    String searchFor;
    String[] values;
    boolean start = true;
    int middle;

    public ArrayBinarySearchSurfaceView(Context context, String searchFor, String[] values){
        super(context);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        this.context = context;
        this.searchFor = searchFor;
        this.values = values;
        String [] fileNames = InputControls.addImageNames(values);
        String searchForImage = InputControls.addImageName(this.searchFor);

        arrayBinarySearchRenderer = new ArrayBinarySearchRenderer(context,values.length,fileNames,searchForImage);
        setRenderer(arrayBinarySearchRenderer);

        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

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

    private void startProcess(){

        try {
            start = false;

            int low = 0;
            int high = values.length-1;
            while(high >= low) {
                middle = (low + high) / 2;
                arrayBinarySearchRenderer.highLight(middle);
                Thread.sleep(500);

                if(values[middle].compareTo(searchFor)==0) {
                    //Success case
                    messages();
                    Toast message = Toast.makeText(context, "They are the same, so we are done.", Toast.LENGTH_LONG);
                    message.show();
                    return;
                }
                arrayBinarySearchRenderer.removeHighLight(middle);
                Thread.sleep(500);
                if(values[middle].compareTo(searchFor) < 0){
                    //searchFor is greater than middle
                    low = middle + 1;
                }
                if(values[middle].compareTo(searchFor) > 0 ) {
                    //searchFor is less than middle
                    high = middle - 1;
                }
            }

            messages();
            Toast message = Toast.makeText(context, "Array doesn't contain "+searchFor, Toast.LENGTH_LONG);
            message.show();
        }catch (Exception e){
            System.err.println("Error in start process");
        }
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