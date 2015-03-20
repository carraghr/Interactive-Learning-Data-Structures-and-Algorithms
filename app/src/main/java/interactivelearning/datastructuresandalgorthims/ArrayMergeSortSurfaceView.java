package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created on 19/03/2015.
 */
public class ArrayMergeSortSurfaceView extends GLSurfaceView{

    Context context;
    String[] values;
    boolean start = true;

    static ArrayMergeSortRenderer arrayMergeSortRenderer;

    public ArrayMergeSortSurfaceView(Context context, String[] values){

        super(context);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        this.context = context;
        this.values = values;
        String [] fileNames = InputControls.addImageNames(values);

        arrayMergeSortRenderer = new ArrayMergeSortRenderer(context,values.length,fileNames);
        setRenderer(arrayMergeSortRenderer);

        //render the view all the time.
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }


    private void startProcess(){

        try {
            start = false;

            mergeSort(values);

            messages();
            Toast message = Toast.makeText(context, "Array doesn't contain ", Toast.LENGTH_LONG);
            message.show();
        }catch (Exception e){
            e.printStackTrace();
            System.err.println("Error in start process");
        }
    }

    public static void mergeSort(String[] values) throws InterruptedException {
        if(values.length > 1) {
            int size1 = values.length/2;
            int size2 = values.length - size1;
            String[] left = new String[size1];
            String[] right = new String[size2];

            for (int i = 0; i < size1; i++) {
                left[i] = values[i];
                arrayMergeSortRenderer.moveLeft(i);
            }

            Thread.sleep(500);

            for (int i = 0; i <= size2; i++) {
                right[i] = values[i + size1];
                arrayMergeSortRenderer.moveRight(i + size1);
            }
            Thread.sleep(500);

            mergeSort(left);
            mergeSort(right);

           // merge(values, left, right);
        }
    }

    // Merges the given left and right arrays into the given
    // result array.  Second, working version.
    // pre : sorted is empty; left/right are sorted
    // post: sorted contains result of merging sorted lists;	

    public static void merge(String[] values, String[] left, String[] right) throws InterruptedException {
        int leftIndex = 0;
        int rightIndex = 0;

        for (int i = 0; i < values.length; i++) {
            if( rightIndex >= right.length ||
                    (leftIndex < left.length && left[leftIndex].compareTo(right[rightIndex]) < 0)){
                values[i] = left[leftIndex];
                //arrayMergeSortRenderer.moveUp(leftIndex);
                Thread.sleep(500);
                leftIndex++;
            }
            else {
                values[i] = right[rightIndex];
                arrayMergeSortRenderer.swap(rightIndex, leftIndex);
                Thread.sleep(500);
                rightIndex++;
            }
        }
    }

    private void messages() {
        Toast message;
        message = Toast.makeText(context, "We check each number and see if its equal to "
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "When they are different, it moves to the next slot in the array."
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
}