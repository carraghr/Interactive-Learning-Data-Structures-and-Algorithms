package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created on 20/03/2015.
 */
public class LinkedListRotationSurfaceView extends GLSurfaceView {

    LinkedListRotationRenderer linkedListRotationRenderer;

    Context context;
    int numberOfRoations, size;
    boolean start = true;


    LinkedListRotationSurfaceView(Context context, int numberOfRotations){

        super(context);

        this.context = context;

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        String [] valuesfileNames = InputControls.addImageNames(SearchValues.LINKEDLIST_ROTATE);

        size = valuesfileNames.length;

        this.numberOfRoations = numberOfRotations;

        linkedListRotationRenderer = new LinkedListRotationRenderer(context,size,valuesfileNames);
        setRenderer(linkedListRotationRenderer);

        //render all the time
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
        try {
            //loop through the amount of rotations to be done
            for (int i = 0; i < numberOfRoations; i++) {
                //get last and second last points
                int last = size -1, secondLast = last -1;

                //move last node down
                linkedListRotationRenderer.moveDown(last);
                Thread.sleep(500);

                //get 2nd last to take last nodes place
                linkedListRotationRenderer.nodeChangeRef(secondLast);
                Thread.sleep(500);

                //move every node from the second last to the right
                for(int j = secondLast; j > -1 ; j-- ){
                    linkedListRotationRenderer.moveRight(j);
                    Thread.sleep(500);
                }

                //move the last node to the start of the linkedlist
                for(int j = 0; j < last; j++){
                    linkedListRotationRenderer.moveLeft(last);
                }
                Thread.sleep(500);

                //change the ref to green
                linkedListRotationRenderer.nodeChangeRef(last);
                Thread.sleep(500);
                //move the node up to start of inside linkedlist.
                linkedListRotationRenderer.moveUp(last);
                Thread.sleep(500);

                //swap all nodes by one place in the renderer to keep every slots the same as on screen
                linkedListRotationRenderer.swapAllNodes();
                Thread.sleep(900);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
