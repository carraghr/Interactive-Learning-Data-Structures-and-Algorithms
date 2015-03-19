package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.widget.Toast;

/**
 * Created on 19/03/2015.
 */
public class LinkedListInsertSurfaceView extends GLSurfaceView {

    private LinkedListInsertRenderer linkedListInsertRenderer;
    private Context context;
    private String index, value;
    private String Values;

    public LinkedListInsertSurfaceView(Context context, String index, String value) {
        super(context);
        this.context = context;
        this.index = index;
        this.value = value;

        String valuefileName = InputControls.addImageName(value);
        String [] valuesfileNames = InputControls.addImageNames(SearchValues.LINKEDLIST_INSERT);

        linkedListInsertRenderer = new LinkedListInsertRenderer();//context,valuesfileNames.length,valuefileName,valuesfileNames);
        setRenderer(linkedListInsertRenderer);

        //render the view only when there is a change in the drawing data
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

}
