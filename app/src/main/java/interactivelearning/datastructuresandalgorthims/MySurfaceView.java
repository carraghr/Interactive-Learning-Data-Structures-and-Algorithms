package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

/**
 * Created by Richard Carragher on 06/03/2015.
 */
public class MySurfaceView extends GLSurfaceView{

    private final MyRenderer myRenderer;

    Context context;

    public MySurfaceView(Context context){
        super(context);
        this.context=context;
        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        //set the Renderer for drawing on the surfaceview
        myRenderer= new MyRenderer(context);
        setRenderer(myRenderer);


        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    private final float TOUCH_SCALE_FACTOR = 180.0f / 320;
    private float mPreviousX;
    private float mPreviousY;

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.

        myRenderer.moveSq();
        requestRender();

        //Toast toast = Toast.makeText(context, "You sure showed that bubble sort button", Toast.LENGTH_SHORT);
        //toast.show();
        return true;
    }
}