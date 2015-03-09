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

    private Square mySquare, mySq;
    private Line myline;

    // myMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];
    Context context;
    int count;

    MyRenderer(Context context){
        super();
        this.context = context;
    }
    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        // Set the background frame colour
        GLES20.glClearColor(0.6f, 0.0f, 0.0f, 0.0f);

        // set up shapes
        mySquare = new Square(new float[]{0.0f,0.0f}, 0.05f, new float[]{1.f,1.f,1.f,1.f});
        //mySq = new Square(new float[]{0.6f,0.0f}, 0.05f, new float[]{1.f,1.f,1.f,1.f});

        //myline = new Line(0.5f,mySq.getRightCenterPoint(),mySquare.getLeftCenterPoint(), new float[]{0.f,0.f,0.f,0.f});
    }

    @Override
    public void onDrawFrame(GL10 unused){

        // Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        // Set the camera position (View matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        // Draw square
        mySquare.draw(mMVPMatrix);
       // if(count >= 1) {
         //   Toast toast = Toast.makeText(context, "Draw frame", Toast.LENGTH_SHORT);
           // toast.show();
        //}
       // mySq.draw(mMVPMatrix);
        //Draw connected line
        //myline.draw(mMVPMatrix);
    }

    @Override
    public void onSurfaceChanged(GL10 unused,int width,int height){
        GLES20.glViewport(0,0,width,height);

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
        mySquare.moveRight(0.5f);
        float [] a = mySquare.getRightCenterPoint();
        String coords ="x = "+ a[0] ;
        count++;
        Toast toast = Toast.makeText(context,coords , Toast.LENGTH_SHORT);
        toast.show();
    }
}