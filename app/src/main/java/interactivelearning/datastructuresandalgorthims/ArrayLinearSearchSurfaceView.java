package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;


/**
 * Created on 14/03/2015.
 */
public class ArrayLinearSearchSurfaceView extends GLSurfaceView{

    //private ArrayLinearSearchRenderer arrayLinearSearchRenderer;
    private ArrayLinearSearchRenderer arrayLinearSearchRenderer;
    Context context;

    String searchFor;
    String[] values;
   boolean repeat = false;

    public ArrayLinearSearchSurfaceView(Context context, String searchFor, String[] values){

        super(context);
        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        this.context = context;
        this.searchFor = searchFor;
        this.values = values;
        String [] fileNames = InputControls.addImageNames(values);
        String searchForImage = InputControls.addImageName(this.searchFor);

        arrayLinearSearchRenderer = new ArrayLinearSearchRenderer(context,5,fileNames,searchForImage);
        setRenderer(arrayLinearSearchRenderer);

        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        //startProcess();
    }

    private void startProcess(){
        //arrayLinearSearchRenderer.setUpSquares();
        //requestRender();
        Toast message;
        //arrayLinearSearchRenderer.addSearchFor(); //add the item been searched for to display.
        //requestRender();
        for(int i=0; i < values.length;i++ ){
            //highlight where you are.
            message = Toast.makeText(context, "We check" + values[i] + " and see if its equal to " + searchFor
                  , Toast.LENGTH_LONG);
            message.show();
            arrayLinearSearchRenderer.highLight(i);
            requestRender();
            //display toast message.

            if(searchFor.equals(values[i])){
              //  message = Toast.makeText(context, "They are the same", Toast.LENGTH_LONG);
              //  message.show();
                repeat = true;
                return;
            }
            arrayLinearSearchRenderer.removeHighLight(i);
            requestRender();
            //message = Toast.makeText(context, "They are different me move to the next"
            //        , Toast.LENGTH_LONG);
          //  message.show();
            arrayLinearSearchRenderer.moveSearchItemNexted(i);
            requestRender();
        }
        repeat = true;
    }


    public boolean onTouchEvent(MotionEvent e) {

        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                arrayLinearSearchRenderer.highLight(1);
                requestRender();
                if(repeat) {
                arrayLinearSearchRenderer.setUpSquares();
                startProcess();
                }
        }
        return true;
    }
}
