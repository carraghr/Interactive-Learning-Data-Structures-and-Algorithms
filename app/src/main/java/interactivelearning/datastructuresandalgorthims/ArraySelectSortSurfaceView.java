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

        String [] fileNames = InputControls.addImageNames(values);

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
            for(int i= 0; i< values.length;i++){
                int min = i;
                for(int j = i+1; j<values.length; j++ ){
                    if(values[min].compareTo(values[j]) > 0){
                        if(min!=i){//if its not the first up move last one back down
                            arraySelectSortRenderer.moveDown(min);
                            Thread.sleep(500);
                        }
                        min = j;
                        arraySelectSortRenderer.moveUp(min);
                        Thread.sleep(500);
                    }
                }
                //arraySelectSortRenderer.moveDown(min);
                if(min!=i) {
                    swapSquares(i, min);
                    swapValues(i,min);
                }
            }
        }catch (Exception e){}
    }

    private void swapSquares(int pointA,int pointB){
        //Get start and end point
        float [] pointsA = arraySelectSortRenderer.getSquareTopPoint(pointA);
        float [] pointsB = arraySelectSortRenderer.getSquareTopPoint(pointB);

        try {
            arraySelectSortRenderer.moveDown(pointA);
            Thread.sleep(500);
            while (!(pointsB[0] == pointsA[0])) {//while Point A's x does not equal point B's old x axis
                arraySelectSortRenderer.moveRight(pointA);
                arraySelectSortRenderer.moveLeft(pointB);
                Thread.sleep(500);
                pointsA = arraySelectSortRenderer.getSquareTopPoint(pointA);
            }
            arraySelectSortRenderer.moveUp(pointA);
            arraySelectSortRenderer.moveDown(pointB);
            Thread.sleep(500);
            arraySelectSortRenderer.swap(pointA, pointB);
            Thread.sleep(500);
        }catch(Exception e){}
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
