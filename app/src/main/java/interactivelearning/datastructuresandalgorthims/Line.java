package interactivelearning.datastructuresandalgorthims;

import android.opengl.GLES20;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Richard Carragher on 08/03/2015.
 */
public class Line {

    private final String VertexShaderCode =
            //Is necessary for drawing
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;"+
                    "}";

    private final String FragmentShaderCode =
            //Is necessary for drawing
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    " gl_FragColor = vColor;" +
                    "}";

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private final int MyProgram;
    private int myPositionHandle;
    private int myColorHandle;
    private int myMVPMatrixHandle;

    //number of coordinate per vertext in this array
    static final int COORDS_PER_VERTEX = 3;

    private float [] lineCoords;

    private final short [] drawOrder ={0, 1, 2, 0, 2, 3}; //order to draw vertices in an
    // anti-clock wise direction

    private final int VertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    float [] color;// Format of RGB Alpha.


    float height;

    public Line(float height,float [] pointA,float [] pointB,float[] color){

        this.height = height;

        this.color = color;
        createLink(pointA,pointB);

        //initialize vertex byte buffer for shape coordinated.
        ByteBuffer bb = ByteBuffer.allocateDirect(lineCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(lineCoords);
        vertexBuffer.position(0);

        //initialize byte buffer for the draw list.
        ByteBuffer drawListB = ByteBuffer.allocateDirect( // number of coordinate values * 2 bytes per short
                                                            drawOrder.length * 2 );
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

    public void createLink(float[] start, float [] end){

        lineCoords = new float[] {start[0], start[1] - height, 0.00f, //LT
                                  start[0], start[1] + height, 0.00f, //LB
                                  end[0], end[1] + height, 0.00f, //RB
                                  end[0], end[1] - height, 0.00f};//RT
    }

    public void changeColor(float[] color){
        this.color = color;
    }

    public void changeBoldness(float height){
        //re-construct coords.
        lineCoords = new float[]{ lineCoords[0], (lineCoords[1] + this.height) - height,  lineCoords[2],
                                  lineCoords[3], (lineCoords[4] - this.height) + height,  lineCoords[5],
                                  lineCoords[6], (lineCoords[7] - this.height) + height,  lineCoords[8],
                                  lineCoords[9], (lineCoords[10] + this.height) - height, lineCoords[11]};

        this.height = height;
    }
    public void draw(float[] mvpMatrix){

        //Add program to OpenGL environment
        GLES20.glUseProgram(MyProgram);

        //get handle to vertex shader vPosition member
        myPositionHandle = GLES20.glGetAttribLocation(MyProgram,"vPosition");

        //Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(myPositionHandle);

        //Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(myPositionHandle,COORDS_PER_VERTEX,
                GLES20.GL_FLOAT,false,
                VertexStride, vertexBuffer);

        //get handle to fragment shader vColor member
        myColorHandle = GLES20.glGetUniformLocation(MyProgram,"vColor");

        //Set color for drawing the triangle
        GLES20.glUniform4fv(myColorHandle, 1, color, 0);

        //get handle to shapes transformation matrix
        myMVPMatrixHandle = GLES20.glGetUniformLocation(MyProgram,"uMVPMatrix");
        MyRenderer.checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(myMVPMatrixHandle, 1, false, mvpMatrix, 0);
        MyRenderer.checkGlError("glUniformMatrix4fv");

        //Draw the square
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        //Disable vertex array
        //GLES20.glDisableVertexAttribArray(myPositionHandle);
    }
}
