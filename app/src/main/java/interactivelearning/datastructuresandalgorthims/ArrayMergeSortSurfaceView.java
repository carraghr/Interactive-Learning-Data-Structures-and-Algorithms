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

    private void messages() {
        Toast message;
        message = Toast.makeText(context, "We check each number and see if its equal to "
                , Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context, "When they are different, it moves to the next slot in the array."
                , Toast.LENGTH_LONG);
        message.show();
    }

    private void startProcess(){

        try {
            start = false;
            mergeSort(values);
            messages();
            Toast message = Toast.makeText(context, "Array doesn't contain ", Toast.LENGTH_LONG);
            message.show();
        }catch (Exception e){
            System.err.println("Error in start process");
        }
    }

    public static void mergeSort(String[] array) {
        if (array.length > 1) {
            // split array into two halves
            String[] left = leftHalf(array);
            String[] right = rightHalf(array);

            // recursively sort the two halves
            mergeSort(left);
            mergeSort(right);

            // merge the sorted halves into a sorted whole
            merge(array, left, right);
        }
    }

    // Returns the first half of the given array.
    public static String[] leftHalf(String[] array) {
        int size1 = array.length / 2;
        arrayMergeSortRenderer.splitLeft(size1);
        String[] left = new String[size1];
        System.arraycopy(array, 0, left, 0, size1);
        return left;
    }

    // Returns the second half of the given array.
    public static String[] rightHalf(String[] array) {
        int size1 = array.length / 2;
        int size2 = array.length - size1;
        String[] right = new String[size2];
        arrayMergeSortRenderer.splitRight(size1);
        System.arraycopy(array, size1, right, 0, size2);
        return right;
    }

    // Merges the given left and right arrays into the given
    // result array.  Second, working version.
    // pre : result is empty; left/right are sorted
    // post: result contains result of merging sorted lists;
    public static void merge(String[] result, String[] left, String[] right) {
        int i1 = 0;   // index into left array
        int i2 = 0;   // index into right array

        for (int i = 0; i < result.length; i++) {
            if (i2 >= right.length || (i1 < left.length && left[i1].compareTo(right[i2])<= 0)) {
                result[i] = left[i1];    // take from left
                i1++;
            }
            else {
                result[i] = right[i2];   // take from right
                i2++;
            }
        }
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
