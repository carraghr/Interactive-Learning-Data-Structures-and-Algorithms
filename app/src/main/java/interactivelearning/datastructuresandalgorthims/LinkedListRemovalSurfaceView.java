package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.widget.Toast;

/**
 * Created on 20/03/2015.
 */
public class LinkedListRemovalSurfaceView extends GLSurfaceView{

    private LinkedListRemovalRenderer linkedListRemovalRenderer;
    private Context context;
    private String index;
    private String[] values;

    public LinkedListRemovalSurfaceView(Context context, String index) {
        super(context);
        this.context = context;
        this.index = index;

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        //String valuefileName = InputControls.addImageName(value);
        String [] valuesfileNames = InputControls.addImageNames(SearchValues.LINKEDLIST_INSERT);

        linkedListRemovalRenderer = new LinkedListRemovalRenderer(context,valuesfileNames.length,valuesfileNames);//,valuefileName);
        setRenderer(linkedListRemovalRenderer);

        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

}

