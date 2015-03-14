package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;

/**
 * Created by Richard Carragher on 06/03/2015.
 */
public class MySurfaceView extends GLSurfaceView{

    private final ArrayDeclarationRenderer arrayDeclarationRenderer;

    Context context;

    public MySurfaceView(Context context,String type, int number_of_slots,String[] values){
        super(context);

        this.context=context;

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        //set the Renderer for drawing on the surfaceview
        if(type.equals("Declaration")) {
            arrayDeclarationRenderer = new ArrayDeclarationRenderer(this.context, number_of_slots);
        }else{
            String [] fileNames = InputControls.addImageNames(values);
            arrayDeclarationRenderer = new ArrayDeclarationRenderer(this.context, number_of_slots,fileNames);
        }
        setRenderer(arrayDeclarationRenderer);
        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);


    }

    /////////
}