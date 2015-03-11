package interactivelearning.datastructuresandalgorthims;

import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Richard on 11/03/2015.
 */
public class ArrayDeclareRenderer implements GLSurfaceView.Renderer {


    private static final String TAG = " ArrayDeclareRenderer";
    private Square[] squares;
    int numberOfSquares;
    String [] fileNames;
    final float radius = 0.075f;


    // myMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    ArrayDeclareRenderer(int numberOfSquares){
        super();
        this.numberOfSquares = numberOfSquares;
    }

    ArrayDeclareRenderer(int numberOfSquares,String [] fileNames){
        super();
        this.numberOfSquares = numberOfSquares;
        this.fileNames = fileNames;
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame colour
        GLES20.glClearColor(0.24f, 0.522f, 0.863f, 0.0f);

        // set up shapes
        setUpSquares();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }

    @Override
    public void onDrawFrame(GL10 gl) {

    }

    private void setUpSquares(){

        float offset;
        int left,right;
        if(numberOfSquares%2!=0){
            squares[numberOfSquares/2] = new Square(new float[]{0.0f,0.0f},radius,new float[]{1.f,1.f,1.f,1.f});
            offset=2*radius;
        }else{
            offset=radius;
        }
        for(right =  numberOfSquares/2 +1,left = numberOfSquares/2 -1; right<numberOfSquares && left >-1; right++, left--){
            squares[right] = new Square(new float[]{ 0.0f - offset ,0.0f},radius,new float[]{1.f,1.f,1.f,1.f});
            squares[left] = new Square(new float[]{ 0.0f + offset,0.0f},radius,new float[]{1.f,1.f,1.f,1.f});
            offset+=(2*radius);
        }
    }
}
