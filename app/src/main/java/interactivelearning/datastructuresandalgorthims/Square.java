package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.opengl.GLES20;
import android.opengl.GLUtils;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Richard on 04/03/2015.
 * Based on Jessica's work
 */
public class Square{

    private String TAG ="Square";

    private final String VertexShaderCode =
            //Is necessary for drawing
            "uniform mat4 uMVPMatrix;" +
            "attribute vec4 vPosition;" +
            "attribute vec2 a_texCoord;" +
            "varying vec2 v_texCoord;" +
            "void main() {" +
            "  gl_Position = uMVPMatrix * vPosition;"+
            "  v_texCoord = a_texCoord;" +
            "}";


    private final String FragmentShaderCode =
            //Is necessary for image
            "precision mediump float;" +
            "varying vec2 v_texCoord;" +
            "uniform sampler2D s_texture;" +
            "void main() {" +
            "  gl_FragColor = texture2D( s_texture, v_texCoord );" +
            "}";

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private static int MyProgram;
    private int myPositionHandle;
    private int myMVPMatrixHandle;
    private int mTexCoordLoc;
    private int mSamplerLoc;

    //number of coordinate per vertext in this array
    static final int COORDS_PER_VERTEX = 3;

    private float [] squareCoords;

    private final short [] drawOrder ={0, 1, 2, 0, 2, 3}; //order to draw vertices in an
                                                          // anti-clock wise direction

    private final int VertexStride = COORDS_PER_VERTEX * 4; // 4 bytes per vertex

    float [] color;// Format of RGB Alpha.

    float [] centerPoint;
    private final static float radius = 0.075f;

    /**
     * Image shaders and coords below.
     */

    private float [] imageVertex = new float[]{0.0f, 0.0f,
                                               0.0f, 1.0f,
                                               1.0f, 1.0f,
                                               1.0f, 0.0f};

    private final FloatBuffer imageBuffer;
    private String imageFilename;
    private Context context;
    private int [] texturenames;
    /**
     * Sets up the drawing object data for use in an OpenGL ES context
     */

    public Square(float [] center,Context context,String imageFilename,int count){

        this.context = context;
        this.centerPoint = center;

        createSquare();

        TAG+=count;

        //initialize vertex byte buffer for shape coordinated.
        ByteBuffer bb = ByteBuffer.allocateDirect(squareCoords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        //initialize byte buffer for the draw list.
        ByteBuffer drawListBb = ByteBuffer.allocateDirect( // number of coordinate values * 2 bytes per short
                                                            drawOrder.length * 2   );
        drawListBb.order(ByteOrder.nativeOrder());
        drawListBuffer = drawListBb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        //prepare the two shaders
        int vertexShader = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, VertexShaderCode);
        int fragmentShader = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, FragmentShaderCode);



        // Image variables
        this.context=context;
        this.imageFilename = imageFilename;

        //The texture buffer
        ByteBuffer imagebb = ByteBuffer.allocateDirect(imageVertex.length * 4);
        imagebb.order(ByteOrder.nativeOrder());
        imageBuffer = imagebb.asFloatBuffer();
        imageBuffer.put(imageVertex);
        imageBuffer.position(0);

        //Generate Textures
        texturenames = new int[1];
        GLES20.glGenTextures(1,texturenames,0);

        //Retrieve image from resources
        int id = context.getResources().getIdentifier(this.imageFilename,"drawable",context.getPackageName());

        //Temporary create a bitmap
        Bitmap bmp= BitmapFactory.decodeResource(context.getResources(), id);

        //bind texture to texture name
        GLES20.glActiveTexture(GLES20.GL_TEXTURE0);
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,texturenames[0]);

        //set filtering
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MIN_FILTER,  GLES20.GL_NEAREST);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D, GLES20.GL_TEXTURE_MAG_FILTER,  GLES20.GL_NEAREST);

        //set wrapping node
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_WRAP_S,GLES20.GL_CLAMP_TO_EDGE);
        GLES20.glTexParameteri(GLES20.GL_TEXTURE_2D,GLES20.GL_TEXTURE_WRAP_T,GLES20.GL_CLAMP_TO_EDGE);

        //load the bitmap into the bound texture
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D,0,bmp,0);
        ShaderLoader.checkGlError("texImage2D");
        //recycle bitmap
        bmp.recycle();

        //prepare OpenGL Program and bind the shaders for square
        MyProgram = GLES20.glCreateProgram();               //create empty OpenGL Program
        GLES20.glAttachShader(MyProgram,vertexShader);      //add the vertex shader to program
        ShaderLoader.checkGlError("glAttachShader");
        GLES20.glAttachShader(MyProgram,fragmentShader);    //add the fragment shader to program
        ShaderLoader.checkGlError("glAttachShader");
        GLES20.glLinkProgram(MyProgram);                    //create OpenGL program executables
        ShaderLoader.checkGlError("glLinkProgram");

    }

    private void createSquare() {
                                    //    x                                   y           z - 2d never changes
        squareCoords = new float[] {(centerPoint[0] + radius), (centerPoint[1] - radius), 0.00f,    //LT
                                    (centerPoint[0] + radius), (centerPoint[1] + radius), 0.00f,    //LB
                                    (centerPoint[0] - radius), (centerPoint[1] + radius), 0.00f,    //RB
                                    (centerPoint[0] - radius), (centerPoint[1] - radius), 0.00f};   //RT

    }

    /**
     * with in the 4 move methods below moveDown, moveUp, moveRight and moveLeft
     * if amount is a - been taking in going through a moveUp or moveRight it will result in a
     * -m - -n = - +n which is wrong and will result in errors in movement.
     * The methods below need some check for amount for error handling
     * TODO ^
     * @param amount
     */
    public void moveDown(float amount){
        centerPoint[1] = centerPoint[1] - amount;
        createSquare();
    }

    public void moveUp(float amount){
        centerPoint[1] = centerPoint[1] + amount;
        createSquare();
    }
    public void moveRight(float amount){
        centerPoint[0] -= amount;
        createSquare();
    }
    public void moveLeft(float amount){
        centerPoint[0] = centerPoint[0] + amount;
        createSquare();
    }

    public float [] getRightCenterPoint(){
        return new float[]{ centerPoint[0] - radius, centerPoint[1], 0.00f};
    }

    public float [] getLeftCenterPoint(){
        return new float[]{ centerPoint[0] + radius, centerPoint[1], 0.00f};
    }

    public float [] getTopCenterPoint(){
        return new float[]{ centerPoint[0], centerPoint[1] - radius, 0.00f};
    }

    public float [] getBottomCenterPoint(){
        return new float[]{ centerPoint[0], centerPoint[1] + radius, 0.00f};
    }



    // which to draw this shape.
    public void draw(float[] mvpMatrix){


        //Add program to OpenGL environment
        GLES20.glUseProgram(MyProgram);
        checkGlError("glUseProgram");
        vertexBuffer.put(squareCoords);
        vertexBuffer.position(0);

        imageBuffer.put(imageVertex);
        imageBuffer.position(0);

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,texturenames[0]);

        //get handle to vertex shader vPosition member
        myPositionHandle = GLES20.glGetAttribLocation(MyProgram,"vPosition");

        //Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(myPositionHandle);

        //Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(myPositionHandle,COORDS_PER_VERTEX,
                                     GLES20.GL_FLOAT,false,
                                     0, vertexBuffer);

        //get handle to texture coordinates location
        mTexCoordLoc = GLES20.glGetAttribLocation(MyProgram,"a_texCoord");

        GLES20.glEnableVertexAttribArray ( mTexCoordLoc );

        GLES20.glVertexAttribPointer(mTexCoordLoc, 2, GLES20.GL_FLOAT, false, 0, imageBuffer);

        //get handler to shapes's transformation matrix
        myMVPMatrixHandle = GLES20.glGetUniformLocation(MyProgram, "uMVPMatrix");
        checkGlError("glGetUniformLocation");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(myMVPMatrixHandle, 1, false, mvpMatrix, 0);
        checkGlError("glUniformMatrix4fv");


        //get handle to textures locations
        mSamplerLoc = GLES20.glGetUniformLocation (MyProgram, "s_texture" );

        //set the sampler texture unit to 0,where we have saved the texture
        GLES20.glUniform1f(mSamplerLoc,0);

        //Draw the square
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length,
                              GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        //Disable vertex array
       // GLES20.glDisableVertexAttribArray(myPositionHandle);
       // GLES20.glDisableVertexAttribArray(mTexCoordLoc);
    }

    public static float getRadius(){
        return radius;
    }

    public void checkGlError(String glOperation){
        int error;

        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}