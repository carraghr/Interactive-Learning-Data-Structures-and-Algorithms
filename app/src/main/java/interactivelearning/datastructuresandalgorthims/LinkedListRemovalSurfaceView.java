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
    private Context context;
    private String index;
    private String[] values;
    private boolean start = true;
    String [] valuesfileNames;

    public LinkedListRemovalSurfaceView(Context context, String index) {
        super(context);
        this.context = context;
        this.index = index;

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        //String valuefileName = InputControls.addImageName(value);
        valuesfileNames = InputControls.addImageNames(SearchValues.LINKEDLIST_INSERT);

        linkedListRemovalRenderer = new LinkedListRemovalRenderer(context,valuesfileNames.length,valuesfileNames);//,valuefileName);
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
        try {
            for (int i = 0; i < index1; i++) {
                linkedListRemovalRenderer.moveUp(i);
                Thread.sleep(500);
                linkedListRemovalRenderer.moveDown(i);
                Thread.sleep(500);
            }

            for(int i = index1; i < valuesfileNames.length; i++){
                linkedListRemovalRenderer.moveLeft(i);
            }
            Thread.sleep(500);
/*
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
            */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

