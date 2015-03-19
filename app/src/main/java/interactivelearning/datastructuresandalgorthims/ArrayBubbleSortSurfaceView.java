package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created on 19/03/2015.
 */
public class ArrayBubbleSortSurfaceView extends GLSurfaceView {

    private ArrayBubbleSortRenderer arrayBubbleSortRenderer;

    private Context context;
    private String [] values;
    private boolean start = true;

    ArrayBubbleSortSurfaceView(Context context,String[] values){
        super(context);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        this.context = context;
        this.values = values;

        String [] fileNames = InputControls.addImageNames(values);

        arrayBubbleSortRenderer = new ArrayBubbleSortRenderer(context, values.length, fileNames);
        setRenderer(arrayBubbleSortRenderer);

        //render all the time
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

    private void startProcess(){
        start = false;
        try {
            String temp;

            for(int i=0; i < values.length; i++){
                for(int j=1; j < (values.length-i); j++){
                    arrayBubbleSortRenderer.moveUp(j-1);
                    Thread.sleep(500);
                    arrayBubbleSortRenderer.moveUp(j);
                    Thread.sleep(500);

                    if(values[j-1].compareTo( values[j]) > 0){
                        //swap the elements!
                        //swapSquares( pointA, pointB);
                        arrayBubbleSortRenderer.moveDown(j-1);
                        Thread.sleep(500);
                        arrayBubbleSortRenderer.moveRight(j-1);
                        arrayBubbleSortRenderer.moveLeft(j);
                        Thread.sleep(500);
                        temp = values[j-1];
                        values[j-1] = values[j];
                        values[j] = temp;
                    }

                }
            }
        }catch (Exception e){
            System.err.println("Error in start process");
        }
    }

    private void swapSquares(int pointA,int pointB){
        //Get start and end point
        float [] pointsA = arrayBubbleSortRenderer.getSquareTopPoint(pointA);
        float [] pointsB = arrayBubbleSortRenderer.getSquareTopPoint(pointB);

        try {
            arrayBubbleSortRenderer.moveDown(pointA);
            Thread.sleep(500);
            while (!(pointsB[0] == pointsA[0])) {//while Point A's x does not equal point B's old x axis
                arrayBubbleSortRenderer.moveRight(pointA);
                arrayBubbleSortRenderer.moveLeft(pointB);
                Thread.sleep(500);
                pointsA = arrayBubbleSortRenderer.getSquareTopPoint(pointA);
            }
            arrayBubbleSortRenderer.moveUp(pointA);
            arrayBubbleSortRenderer.moveDown(pointB);
            Thread.sleep(500);
            arrayBubbleSortRenderer.swap(pointA, pointB);
            Thread.sleep(500);
        }catch(Exception e){
            System.err.println("Error in swap squares");
        }
        //do a swap with the actually array of squares so everything is the same.
    }

    private void swapValues(int pointA,int pointB){
        String temp = values[pointA];
        values[pointA] = values[pointB];
        values[pointB]=temp;
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