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
    boolean found = false;

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

            while(!found) {
                int position = ArrayBinarySearchRenderer.split(values.length);
                arrayBinarySearchRenderer.highLight(position);

                if (searchFor.compareTo(values[position])== 0) {
                    messages();
                    Toast message = Toast.makeText(context, "They are the same, so we are done.", Toast.LENGTH_LONG);
                    message.show();
                    return;
                }

                else if(searchFor.compareTo(values[position])< 0){
                    //Split on the left side of the array
                    for(int i=0; i < position ; i++){
                        arrayBinarySearchRenderer.highLight(i);
                    }
                    arrayBinarySearchRenderer.split(position-1);
                }
                else{
                    //Split on the right side of the array
                    for(int i=position+1; i > values.length ; i++){
                       arrayBinarySearchRenderer.highLight(i);
                    }
                    arrayBinarySearchRenderer.split(position)
                }
            }
            /*
            for (int i = 0; i < values.length; i++) {
                //highlight where you are.
                arrayBinarySearchRenderer.highLight(i);
                Thread.sleep(500);
                if (searchFor.equals(values[i])) {
                    messages();
                    Toast message = Toast.makeText(context, "They are the same, so we are done.", Toast.LENGTH_LONG);
                    message.show();
                    return;
                }
                arrayBinarySearchRenderer.removeHighLight(i);
                arrayBinarySearchRenderer.moveSearchItemNext(i);
                Thread.sleep(500);
            }
            */
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