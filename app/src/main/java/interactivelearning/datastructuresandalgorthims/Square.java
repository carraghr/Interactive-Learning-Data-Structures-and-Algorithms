package interactivelearning.datastructuresandalgorthims;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Richard on 04/03/2015.
 */
public class Square{

    private final String VertexShaderCode =
            //Is necessary for drawing
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "void main() {" +
            " gl_Position = uMVPMatrix * v{psition;"+"" +
            "}";

    private final String FragmentShaderCode =
            //Is necessary for drawing
           "precision mediump float;" +
           "uniform vec4 vColor;" +
           "void main() {" +
           " gl_FragColor = vColor;" +
           "}";

    private final FloatBuffer vertextBuffer;
    private final ShortBuffer drawListBuffer;
    private final int MyProgram;
    private int myPositionHandle;
    private int myColorHandle;
    private int myMVPMatrixHandle;

    //number of coordinate per vertext in this array
    static final int COORDS_PER_VERTEX = 3;

    float [] squareCoords = {0.00f , 0.00f, 0.00f,//top left
                             0.00f , 0.00f, 0.00f,//bottom left
                             0.00f , 0.00f, 0.00f,//bottom right
                             0.00f , 0.00f, 0.00f};//top right

    private final short [] drawOrder ={0,1,3,0,2,3}; //order to draw vertices in an
                                                    // anti-clock wise direction

    private final int VertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    float [] colour;// Format of RGB Alpha.

    float [] centerPoint;
    float radius;

    /**
     * Sets up the drawing object data for use in an OpenGL ES context
     */

    public Square(float [] center, float radius, float [] color){

        this.centerPoint = center;
        this.radius = radius;

        recreateSquare();

        //initialize vertex byte buffer for shape coordinated.
        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());

        vertextBuffer = bb.asFloatBuffer();
        vertextBuffer.put(squareCoords);
        vertextBuffer.position(0);

        //initialize byte buffer for the draw list.
        ByteBuffer drawListB = ByteBuffer.allocateDirect( // number of coordinate values * 2 bytes per short
                                                            drawOrder.length * 2   );
        drawListB.order(ByteOrder.nativeOrder());
        drawListBuffer = drawListB.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        //prepare the two shaders
        int vertexShader = MyRenderer.loadShader(GLES20.GL_VERTEX_SHADER, VertexShaderCode);
        int fragmentShader = MyRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, FragmentShaderCode);

        //prepare OpenGL Program and bind the shaders
        MyProgram = GLES20.glCreateProgram();               //create empty OpenGL Program
        GLES20.glAttachShader(MyProgram,vertexShader);      //add the vertex shader to program
        GLES20.glAttachShader(MyProgram,fragmentShader);    //add the fragment shader to program
        GLES20.glLinkProgram(MyProgram);                    //create OpenGL program executables
    }

    private void recreateSquare() {

        /**
         * so we start with a center point and a radius from there we want to create four sides/corners
         * we do this by moving out to the side by radius this will get the sides left and right x points
         * to get the y points we go up and down from the sides
         * REMEMBER THAT EVERYTHING IS INVERTED.
         */
                                    //    x                                   y           z - 2d never changes
        squareCoords = new float[] {(centerPoint[0] + radius), (centerPoint[1] - radius), 0.00f,
                                    (centerPoint[0] + radius), (centerPoint[1] + radius), 0.00f,
                                    (centerPoint[0] - radius), (centerPoint[1] + radius), 0.00f,
                                    (centerPoint[0] - radius), (centerPoint[1] - radius), 0.00f};


    }


    //@param mvpMatrix - The model View project matrix fin
    // which to draw this shape.
    public void draw(){

       //Add program to OpenGL environment
        GLES20.glUseProgram(MyProgram);

        //get handle to vertex shader vPosition member
        myPositionHandle = GLES20.glGetAttribLocation(MyProgram,"vPosition");

        //Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(myPositionHandle);

        //Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(myPositionHandle,COORDS_PER_VERTEX,
                                     GLES20.GL_FLOAT,false,
                                     VertexStride,vertextBuffer);

        //get handle to fragment shader vColor member
        myColorHandle = GLES20.glGetUniformLocation(MyProgram,"vColor");

        //Set color for drawing the triangle
        GLES20.glUniform4fv(myColorHandle,1,colour,0);

        //get handle to shapes transformation matrix
        myMVPMatrixHandle = GLES20.glGetUniformLocation(MyProgram,"uMVPMatrix");
        MyRenderer.checkGlError("glGetUniformLocation");

        //Draw the square
        GLES20.glDrawElements(GLES20.GL_TRIANGLES,drawOrder.length,
                              GLES20.GL_UNSIGNED_SHORT,drawListBuffer);

        //Disable vertex array
        GLES20.glDisableVertexAttribArray(myPositionHandle);
    }
}