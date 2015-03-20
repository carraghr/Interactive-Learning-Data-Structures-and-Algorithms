package interactivelearning.datastructuresandalgorthims;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created on 19/03/2015.
 */
public class NodeRef {

    private final String TAG = "NodeItem";

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private int myColorHandle;
    private static int MyProgram;

    //number of coordinate per vertext in this array
    static final int COORDS_PER_VERTEX = 3;
    private float [] coords;
    static final short [] drawOrder ={0, 1, 2, 0, 2, 3}; //order to draw vertices in an
                                                         // anti-clock wise direction

    float [] centerPoint;
    private float width= 0.015f, height= 0.075f;

    private boolean ref;
    float [] color;

    NodeRef(float [] center, boolean ref){

        this.centerPoint = center;
        this.ref = ref;

        createNodeReference();

        //initialize vertex byte buffer for shape coordinated.
        ByteBuffer bb = ByteBuffer.allocateDirect(coords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(coords);
        vertexBuffer.position(0);

        //initialize byte buffer for the draw list.
        //number of coordinate values * 2 bytes per short
        ByteBuffer drawListBb = ByteBuffer.allocateDirect( drawOrder.length * 2 );

        drawListBb.order(ByteOrder.nativeOrder());
        drawListBuffer = drawListBb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        //prepare the two shaders
        int vertexShader = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, ShaderLoader.VertexShaderCodeColor);
        int fragmentShader = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, ShaderLoader.FragmentShaderCodeColor);

        //prepare OpenGL Program and bind the shaders for square
        MyProgram = GLES20.glCreateProgram();               //create empty OpenGL Program
        GLES20.glAttachShader(MyProgram,vertexShader);      //add the vertex shader to program
        ShaderLoader.checkGlError("glAttachShader");
        GLES20.glAttachShader(MyProgram,fragmentShader);    //add the fragment shader to program
        ShaderLoader.checkGlError("glAttachShader");

        GLES20.glLinkProgram(MyProgram);                    //create OpenGL program executables
    }

    public void createNodeReference() {
                              //    x                                   y           z - 2d never changes
        coords = new float[] {(centerPoint[0] + width), (centerPoint[1] - height), 0.00f,    //LT
                              (centerPoint[0] + width), (centerPoint[1] + height), 0.00f,    //LB
                              (centerPoint[0] - width), (centerPoint[1] + height), 0.00f,    //RB
                              (centerPoint[0] - width), (centerPoint[1] - height), 0.00f};   //RT
    }

    public void draw(float[] mvpMatrix){

        GLES20.glUseProgram(MyProgram);
        vertexBuffer.put(coords);
        vertexBuffer.position(0);

        if(ref) {
            color = new float[]{0.196078f , 0.8f , 0.196078f, 0f };
        }else{
            color = new float[]{0.80f, 0f, 0.f, 0f };
        }
        //get handle to vertex shader vPosition member
        int myPositionHandle = GLES20.glGetAttribLocation(MyProgram, "vPosition");

        //Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(myPositionHandle);

        //Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(
                myPositionHandle, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                COORDS_PER_VERTEX * 4, vertexBuffer);

        // get handle to fragment shader's vColor member
        myColorHandle = GLES20.glGetUniformLocation(MyProgram, "vColor");

        // Set color for drawing the triangle
        GLES20.glUniform4fv(myColorHandle, 1, color, 0);

        // get handle to shape's transformation matrix
        myPositionHandle = GLES20.glGetUniformLocation(MyProgram, "uMVPMatrix");

        int myMVPMatrixHandle = GLES20.glGetUniformLocation(MyProgram, "uMVPMatrix");
        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(myMVPMatrixHandle, 1, false, mvpMatrix, 0);

        // Draw the triangle
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        // Disable vertex array
        //GLES20.glDisableVertexAttribArray(myPositionHandle);
    }

    public void change(boolean type){
        ref=type;
    }

    public void moveDown(float amount){
        centerPoint[1] -= (height * amount);
        createNodeReference();
    }
    public void moveUp(float amount){
        centerPoint[1] += (height * amount);
        createNodeReference();
    }
    public void moveRight(float amount){
        centerPoint[0] -= ( width * amount);
        createNodeReference();
    }
    public void moveLeft(float amount){
        centerPoint[0] += ( width * amount);
        createNodeReference();
    }
}
