package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;
import android.widget.Toast;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Richard on 04/03/2015.
 */
public class MyRenderer implements GLSurfaceView.Renderer{

    private static final String TAG = "MyRenderer";

    private Square[] squares;
    int numberOfSquares;
    String [] fileNames;

    Context context;

    // myMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    MyRenderer(Context context, int numberOfSquares){
        super();
        this.context = context;
        this.numberOfSquares = numberOfSquares;
        fileNames = new String[this.numberOfSquares];
        Toast toast = Toast.makeText(context, "Everything set to 0"
                , Toast.LENGTH_SHORT);
        toast.show();
        for(int i=0; i < this.numberOfSquares; i++) {
            fileNames[i] = "number_0";
        }

        squares = new Square[this.numberOfSquares];
        setUpSquares();
    }

    MyRenderer(Context context, int numberOfSquares, String [] fileNames){

        super();
        this.context = context;
        this.numberOfSquares = numberOfSquares;
        this.fileNames = fileNames;

        squares = new Square[numberOfSquares];
        setUpSquares();

    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        // Set the background frame colour
        GLES20.glClearColor(0.24f, 0.522f, 0.863f, 0.0f);

        // set up shapes
        setUpSquares();

    }

    @Override
    public void onDrawFrame(GL10 unused){

        //Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //Set the camera position (View Matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        for(int i=0;i<numberOfSquares;i++) {
            if( squares[i]!=null)
                squares[i].draw(mMVPMatrix);
        }
    }

    @Override
    public void onSurfaceChanged(GL10 unused,int width,int height){
        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width/height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);
    }

    public static int loadShader(int type,String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)

        int shader = GLES20.glCreateShader(type);

        GLES20.glShaderSource(shader,shaderCode);
        GLES20.glCompileShader(shader);

        return shader;
    }

    public static void checkGlError(String glOperation){
        int error;

        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }

    public void moveSq(){
        squares[0].moveLeft(0.01f);
        squares[1].moveDown(0.01f);
    }
    private void setUpSquares(){

        float offset = 0.001f;
        int left,right;
        float radius =Square.getRadius();
        if(numberOfSquares%2!=0){
            squares[numberOfSquares/2] = new Square(new float[]{0.0f,0.0f},context,fileNames[numberOfSquares/2]);
            offset+=2*radius;
            right =  numberOfSquares/2+1;
        }else{
            offset+=radius;
            right =  numberOfSquares/2;
        }
        for(left = numberOfSquares/2 -1; right<numberOfSquares && left >-1; right++, left--){
            squares[right] = new Square(new float[]{ 0.0f - offset ,0.0f},context,fileNames[right]);
            squares[left] = new Square(new float[]{ 0.0f + offset,0.0f},context,fileNames[left]);
            offset+=(2*radius)+0.002f;
        }
    }
}