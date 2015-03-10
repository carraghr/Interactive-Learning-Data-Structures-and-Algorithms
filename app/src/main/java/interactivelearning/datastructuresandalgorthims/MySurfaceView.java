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
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        // MotionEvent reports input details from the touch screen
        // and other input controls. In this case, you are only
        // interested in events where the touch position changed.
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                myRenderer.moveSq();
               requestRender();
        }
        return true;
    }
}