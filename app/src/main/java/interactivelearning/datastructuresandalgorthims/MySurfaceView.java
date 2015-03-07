package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Richard Carragher on 06/03/2015.
 */
public class MySurfaceView extends GLSurfaceView{

    private final MyRenderer myRenderer;

    public MySurfaceView(Context context){
        super(context);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        //set the Renderer for drawing on the surfaceview
        myRenderer= new MyRenderer();
        setRenderer(myRenderer);


        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
    }
}
