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

        //set up image files for nodes
        String valuefileName = InputControls.addImageName(value);
        String [] valuesfileNames = InputControls.addImageNames(SearchValues.LINKEDLIST_INSERT);

        numberOfNodes = valuesfileNames.length;

        //in the insert index is bigger then the number of nodes make it the number of nodes since its going to be last.
        if(this.index >= numberOfNodes){
            this.index = numberOfNodes;
        }

        //set up renderer
        linkedListInsertRenderer = new LinkedListInsertRenderer(context,valuesfileNames.length,valuesfileNames,valuefileName);
        setRenderer(linkedListInsertRenderer);

        //render the all the time
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

    //messages to be displayed at the end of the process.
    private void messages() {
        Toast message;
        message = Toast.makeText(context, "We go to the index by traveling through all the nodes in front off the index "
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "when we reach the index we insert the value and change any references as we need to."
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
            //get node to index
            for (int i = 0; i < index; i++) {
                linkedListInsertRenderer.moveInsertNext();
                linkedListInsertRenderer.moveUp(i);
                Thread.sleep(500);
                linkedListInsertRenderer.moveDown(i);
                Thread.sleep(500);
            }
            //if the index is at the last one change its ref to red and the old last to green
            if(index == numberOfNodes){
                //update the list
                linkedListInsertRenderer.nodeChangeRef(index-1);
                linkedListInsertRenderer.changeinsertRef();
            }
            //move any nodes forwards to make room
            for(int i = index ; i < numberOfNodes ; i++ ){
                linkedListInsertRenderer.moveRight(i);
                Thread.sleep(500);
            }
            //move node down into place with all the other nodes.
            linkedListInsertRenderer.moveInsertDown();
            Thread.sleep(500);
            linkedListInsertRenderer.moveInsertDown();
            Thread.sleep(500);
            messages();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
