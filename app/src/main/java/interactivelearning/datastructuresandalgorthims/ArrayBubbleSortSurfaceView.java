package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created on 19/03/2015.
 */
public class ArrayBubbleSortSurfaceView extends GLSurfaceView {

    private ArrayBubbleSortRenderer arrayBubbleSortRenderer;

    private Context context;
    private String [] values;
    private boolean start = true;

    ArrayBubbleSortSurfaceView(Context context,String[] values){

        //call constructor for view
        super(context);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        //set up variables.
        this.context = context;
        this.values = values;

        //get image names for input to be shown.
        String [] fileNames = InputControls.addImageNames(values);

        //set up renderer for surfaceView and set the renderer.
        arrayBubbleSortRenderer = new ArrayBubbleSortRenderer(context, values.length, fileNames);
        setRenderer(arrayBubbleSortRenderer);

        //continue to render at all times.
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        //Message to tell user to tap screen to start the diagram.
        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

    private void startProcess(){
        start = false;
        try {
            for(int i=0; i < values.length; i++){
                for(int j=1; j < (values.length-i); j++){
                    arrayBubbleSortRenderer.moveUp(j-1);
                    arrayBubbleSortRenderer.moveUp(j);
                    Thread.sleep(500);

                    if(values[j-1].compareTo( values[j]) > 0){
                        //swap the elements
                        swapSquares(j);
                    }
                    else{
                        //Replace raised squares and move along
                        arrayBubbleSortRenderer.moveDown(j);
                        arrayBubbleSortRenderer.moveDown(j-1);
                        Thread.sleep(500);
                    }
                }
            }
        }catch (Exception e){
            System.err.println("Error in start process");
        }
    }

    //method to swap two pairs of squares.
    public void swapSquares(int j){
        arrayBubbleSortRenderer.moveDown(j-1);
        try {
            Thread.sleep(500);
            arrayBubbleSortRenderer.moveRight(j-1);
            Thread.sleep(500);
            arrayBubbleSortRenderer.moveLeft(j);
            Thread.sleep(500);
            arrayBubbleSortRenderer.moveDown(j);
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.err.println("Error in swapSquares");
        }
        String temp = values[j-1];
        values[j-1] = values[j];
        values[j] = temp;
        arrayBubbleSortRenderer.swap(j-1,j);
    }

    //Messages to be shown after diagrams process is finished.
    private void messages() {
        Toast message;
        message = Toast.makeText(context, "We check two numbers and see if they're in order"
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "When they are, it moves up one position in the array."
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "If not, they're swapped, and it moves on"
                , Toast.LENGTH_LONG);
        message.show();
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