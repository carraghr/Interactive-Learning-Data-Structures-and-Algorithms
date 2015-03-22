package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created on 18/03/2015.
 */
public class ArraySelectSortSurfaceView extends GLSurfaceView{

    private ArraySelectSortRenderer arraySelectSortRenderer;

    private Context context;
    private String [] values;
    private boolean start = true;

    ArraySelectSortSurfaceView(Context context,String[] values){
        super(context);

        //Create an OpenGL ES 2.0 context.
        setEGLContextClientVersion(2);

        this.context = context;
        this.values = values;

        //add image file to be used by renderer
        String [] fileNames = InputControls.addImageNames(values);

        //create and set renderer
        arraySelectSortRenderer = new ArraySelectSortRenderer(context,values.length,fileNames);
        setRenderer(arraySelectSortRenderer);

        //render all the time
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

        Toast toast = Toast.makeText(context,"Please tap the screen to begin !",Toast.LENGTH_SHORT);
        toast.show();
    }

    private void startProcess(){
        start = false;
        try {
            //loop through the array form start to finish
            for(int i= 0; i< values.length;i++){
                //set the first element as the min
                int min = i;
                //move up selected min
                arraySelectSortRenderer.moveUp(min);
                Thread.sleep(500);
                //move forward from i and test each element in the array.
                for(int j = i+1; j<values.length; j++ ){

                    //if min is grater then anything else make j the new min
                    if(values[min].compareTo(values[j]) > 0){
                        arraySelectSortRenderer.moveDown(min);
                        Thread.sleep(500);

                        min = j;
                        //highlight new min
                        arraySelectSortRenderer.moveUp(min);
                        Thread.sleep(500);
                    }
                }
                //if min isn't the same as it started then swap elements
                if(min!=i) {
                    swapSquares(i, min);
                    swapValues(i,min);
                }
                else{
                    arraySelectSortRenderer.moveDown(min);
                }
            }
            messages();
        }catch (Exception e){}
    }

    public void messages(){
        Toast message = Toast.makeText(context,"Get first element and go though the array looking for another one that is smaller",Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context,"when we find a smaller one continue to end of the array looking for something smaller",Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context,"when we reach the end swap the start position with the smallest",Toast.LENGTH_LONG);
        message.show();
        message = Toast.makeText(context,"move on to the next element and continue till you reach the end of the array",Toast.LENGTH_LONG);
        message.show();
    }

    private void swapSquares(int pointA,int pointB){

        //Get start and end point
        float [] pointsA = arraySelectSortRenderer.getSquareTopPoint(pointA);
        float [] pointsB = arraySelectSortRenderer.getSquareTopPoint(pointB);

        try {
            //move a down
            arraySelectSortRenderer.moveDown(pointA);
            Thread.sleep(500);

            //while a doesnt match b's x point move both in the opposite side ie circle motion
            while (!(pointsB[0] == pointsA[0])) {//while Point A's x does not equal point B's old x axis
                arraySelectSortRenderer.moveRight(pointA);
                arraySelectSortRenderer.moveLeft(pointB);
                Thread.sleep(500);
                pointsA = arraySelectSortRenderer.getSquareTopPoint(pointA);
            }
            //slot back a and b into the array
            arraySelectSortRenderer.moveUp(pointA);
            arraySelectSortRenderer.moveDown(pointB);
            Thread.sleep(500);

            //in render swap the two sqaures so they are the same position for value and squares.
            arraySelectSortRenderer.swap(pointA, pointB);
            Thread.sleep(500);

        }catch(Exception e){}
    }

    //swap values in value array
    private void swapValues(int pointA,int pointB){
        String temp = values[pointA];
        values[pointA] = values[pointB];
        values[pointB]=temp;
    }

    //on touch event to start diagram
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
