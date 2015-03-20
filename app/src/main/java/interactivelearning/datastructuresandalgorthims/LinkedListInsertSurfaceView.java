package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created on 19/03/2015.
 */
public class LinkedListInsertSurfaceView extends GLSurfaceView {

    private LinkedListInsertRenderer linkedListInsertRenderer;
    private Context context;
    private String value;
    private int index, numberOfNodes;
    private String Values;
    private boolean start = true;

    public LinkedListInsertSurfaceView(Context context, String index, String value) {
        super(context);
        this.context = context;
        this.index = Integer.parseInt(index);
        this.value = value;

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        String valuefileName = InputControls.addImageName(value);
        String [] valuesfileNames = InputControls.addImageNames(SearchValues.LINKEDLIST_INSERT);
        numberOfNodes = valuesfileNames.length;
        if(this.index >= numberOfNodes){
            this.index = numberOfNodes;
        }

        linkedListInsertRenderer = new LinkedListInsertRenderer(context,valuesfileNames.length,valuesfileNames,valuefileName);
        setRenderer(linkedListInsertRenderer);

        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }
    private void messages() {
        Toast message;
        message = Toast.makeText(context, "We check each number and see if its equal to "
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "When they are different, it moves to the next slot in the array."
                , Toast.LENGTH_LONG);
        message.show();
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

    private void startProcess() {
        start = false;
        try {
            for (int i = 0; i < index; i++) {
                linkedListInsertRenderer.moveInsertNext();
                Thread.sleep(500);
            }

            if(index == numberOfNodes){
                //update the list
                linkedListInsertRenderer.nodeChangeRef(index-1);
                linkedListInsertRenderer.changeinsertRef();
            }

            for(int i = index ; i < numberOfNodes ; i++ ){
                linkedListInsertRenderer.moveRight(i);
                Thread.sleep(500);
            }

            linkedListInsertRenderer.moveInsertDown();
            Thread.sleep(500);
            linkedListInsertRenderer.moveInsertDown();
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
