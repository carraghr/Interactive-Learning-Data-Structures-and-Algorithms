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

        size = 5;

        this.numberOfRoations = 1;

        linkedListRotationRenderer = new LinkedListRotationRenderer(context,size,valuesfileNames);
        setRenderer(linkedListRotationRenderer);

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
        try {
            for (int i = 0; i < numberOfRoations; i++) {
                int last = size -1, secondLast = last -1;
                linkedListRotationRenderer.moveDown(last);
                Thread.sleep(500);
                linkedListRotationRenderer.nodeChangeRef(secondLast);
                Thread.sleep(500);
                for(int j = secondLast; j > -1 ; j-- ){
                    linkedListRotationRenderer.moveRight(j);
                    Thread.sleep(500);
                }
                for(int j = 0; j < last; j++){
                    linkedListRotationRenderer.moveLeft(last);
                    Thread.sleep(500);
                }
                linkedListRotationRenderer.nodeChangeRef(last);
                Thread.sleep(500);
                linkedListRotationRenderer.moveUp(last);
                Thread.sleep(500);
                linkedListRotationRenderer.swapAllNodes();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
