package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created on 20/03/2015.
 */
public class LinkedListRemovalSurfaceView extends GLSurfaceView{

    private LinkedListRemovalRenderer linkedListRemovalRenderer;
    private String index;
    private boolean start = true;
    String [] valueFileNames;

    public LinkedListRemovalSurfaceView(Context context, String index) {
        super(context);
        this.index = index;

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        valueFileNames = InputControls.addImageNames(SearchValues.LINKEDLIST_INSERT);

        linkedListRemovalRenderer = new LinkedListRemovalRenderer(context, valueFileNames.length, valueFileNames);
        setRenderer(linkedListRemovalRenderer);

        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
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
        int index1 = Integer.parseInt(index);
        if(index1 >= valueFileNames.length){
            index1 = valueFileNames.length - 1;
        }
        try {
            //Highlight which square you are looking at
            for (int i = 0; i < index1; i++) {
                linkedListRemovalRenderer.moveUp(i);
                Thread.sleep(500);
                linkedListRemovalRenderer.moveDown(i);
                Thread.sleep(500);
            }
            //If the index is the last one, change the second last nodes colour
            //To show that it does not connect to anything
            if(index1 == valueFileNames.length-1) {
                linkedListRemovalRenderer.removeNode(index1);
                linkedListRemovalRenderer.nodeChangeRef(index1-1);
            }
            else {
                //Otherwise, just move all the squares to the left
                for (int i = index1 + 1; i < valueFileNames.length; i++) {
                    linkedListRemovalRenderer.moveLeft(i);
                }
            }
            Thread.sleep(500);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }
}

