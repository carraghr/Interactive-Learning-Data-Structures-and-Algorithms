package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created on 20/03/2015.
 */
public class LinkedListRemovalRenderer implements GLSurfaceView.Renderer {
    private Node [] nodes;
    private String [] fileNames;

    private Node insertValue;
    //private String insertValueFileName;

    private int numberOfNodes;
    private Context context;

    // myMVPMatrix is an abbreviation for "Model View Projection Matrix"
    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    LinkedListRemovalRenderer(Context context, int numberOfNodes, String[] fileNames){ //,String insertValueFileName){

        super();
        this.context = context;

        this.numberOfNodes = numberOfNodes;
        nodes = new Node[this.numberOfNodes];
        this.fileNames = fileNames;

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {

        // Set the background frame colour
        GLES20.glClearColor(0.24f, 0.522f, 0.863f, 0.0f);

        // set up squares
        setUpNodes();

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

        for(int i=0;i<numberOfNodes;i++) {
            nodes[i].draw(mMVPMatrix);
        }
        if(!(insertValue == null)){
            insertValue.draw(mMVPMatrix);
        }
    }

    private void setUpNodes(){
        float offset = 0.075f;
        int left,right;
        float radius = Square.getRadius();
        if(numberOfNodes%2!=0 && numberOfNodes>0){
            right =  numberOfNodes/2+1;
            nodes[numberOfNodes/2] = new Node(new float[]{0.0f,0.0f},fileNames[numberOfNodes/2],context,!(right ==  numberOfNodes -1));
            offset+=offset;
        }else{
            right =  numberOfNodes/2;
        }
        for(left = numberOfNodes/2 - 1; right<numberOfNodes && left >-1; right++, left--){
            nodes[right] = new Node(new float[]{0.0f - offset, 0.0f},fileNames[right], context,!(right ==  numberOfNodes -1) );
            nodes[left] = new Node(new float[]{ 0.0f + offset,0.0f}, fileNames[left], context, true);
            offset+=(2*radius);
        }
    }
}
