package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created on 11/03/2015.
 */
public class ArrayDeclarationRenderer implements GLSurfaceView.Renderer {

    private Square[] squares;
    int numberOfSquares;
    String [] fileNames;

    Context context;

    // myMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    ArrayDeclarationRenderer(Context context, int numberOfSquares){
        super();
        this.context = context;
        this.numberOfSquares = numberOfSquares;
        fileNames = new String[numberOfSquares];

        for(int i=0; i < numberOfSquares; i++) {
            fileNames[i] = "number_0";
        }

        squares = new Square[numberOfSquares];
    }

    ArrayDeclarationRenderer(Context context, int numberOfSquares, String [] fileNames){
        super();
        this.context = context;
        this.numberOfSquares = numberOfSquares;
        this.fileNames = fileNames;

        squares = new Square[numberOfSquares];
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame colour
        GLES20.glClearColor(0.24f, 0.522f, 0.863f, 0.0f);

        // set up squares
        setUpSquares();
    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width/height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

    }

    @Override
    public void onDrawFrame(GL10 gl) {

        //Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //Set the camera position (View Matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        for(int i=0;i<numberOfSquares;i++) {
            squares[i].draw(mMVPMatrix);
        }

    }

    private void setUpSquares() {

        float offset = 0.001f;
        int left,right;
        float radius = Square.getRadius();
        if(numberOfSquares%2!=0){
            squares[numberOfSquares/2] = new Square(new float[]{0.0f,0.0f},context,fileNames[numberOfSquares/2],numberOfSquares/2);
            offset+=2*radius;
            right =  numberOfSquares/2+1;
        }else{
            offset+=radius;
            right =  numberOfSquares/2;
        }
        for(left = numberOfSquares/2 - 1; right<numberOfSquares && left >-1; right++, left--){
            squares[right] = new Square(new float[]{ 0.0f - offset ,0.0f},context,fileNames[right],right);
            squares[left] = new Square(new float[]{ 0.0f + offset,0.0f},context,fileNames[left],left);
            offset+=(2*radius)+0.002f;
        }
    }
}
