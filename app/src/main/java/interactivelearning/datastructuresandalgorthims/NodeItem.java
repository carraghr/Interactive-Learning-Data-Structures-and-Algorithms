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
 * Created on 19/03/2015.
 */
public class NodeItem {

    final String TAG = "NodeItem";

    private final FloatBuffer vertexBuffer;
    private final ShortBuffer drawListBuffer;
    private static int MyProgram;

    //number of coordinate per vertext in this array
    static final int COORDS_PER_VERTEX = 3;

    private float [] coords;

    static final short [] drawOrder ={0, 1, 2, 0, 2, 3}; //order to draw vertices in an
                                                         // anti-clock wise direction

    float [] centerPoint;

    private float width = 0.06f, height = 0.075f;

    private float [] imageVertex = new float[]{0.0f, 0.0f,
                                                0.0f, 1.0f,
                                                1.0f, 1.0f,
                                                1.0f, 0.0f};

    private final FloatBuffer imageBuffer;
    private Context context;
    private int [] texturenames;

    NodeItem(float [] center,Context context,String imageFilename){

        this.context = context;
        this.centerPoint = center;

        createNodeItem();

        //initialize vertex byte buffer for shape coordinated.
        ByteBuffer bb = ByteBuffer.allocateDirect(coords.length * 4);
        bb.order(ByteOrder.nativeOrder());
        vertexBuffer = bb.asFloatBuffer();
        vertexBuffer.put(coords);
        vertexBuffer.position(0);

        // initialize byte buffer for the draw list.
        // number of coordinate values * 2 bytes per short
        ByteBuffer drawListBb = ByteBuffer.allocateDirect( drawOrder.length * 2 );

        drawListBb.order(ByteOrder.nativeOrder());
        drawListBuffer = drawListBb.asShortBuffer();
        drawListBuffer.put(drawOrder);
        drawListBuffer.position(0);

        //prepare the two shaders
        int vertexShader = ShaderLoader.loadShader(GLES20.GL_VERTEX_SHADER, ShaderLoader.VertexShaderCode);
        int fragmentShader = ShaderLoader.loadShader(GLES20.GL_FRAGMENT_SHADER, ShaderLoader.FragmentShaderCode);

        // Image variables

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
        int id = context.getResources().getIdentifier(imageFilename,"drawable",context.getPackageName());

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
        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bmp, 0);
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

        int[] linkStatus = new int[1];

        GLES20.glGetProgramiv(MyProgram, GLES20.GL_LINK_STATUS, linkStatus, 0);

        if (linkStatus[0] != GLES20.GL_TRUE) {
            Log.e(TAG, "Could not link program: ");
            Log.e(TAG, GLES20.glGetProgramInfoLog(MyProgram));
            GLES20.glDeleteProgram(MyProgram);
            MyProgram = 0;
        }
    }

    public void createNodeItem() {
                                //    x                                   y           z - 2d never changes
        coords = new float[] {(centerPoint[0] + width), (centerPoint[1] - height), 0.00f,    //Left Top
                              (centerPoint[0] + width), (centerPoint[1] + height), 0.00f,    //Left Bottom
                              (centerPoint[0] - width), (centerPoint[1] + height), 0.00f,    //Right Bottom
                              (centerPoint[0] - width), (centerPoint[1] - height), 0.00f};   //Right Top
    }

    // called by render to draw part of a node.
    public void draw(float[] mvpMatrix){

        //link opengl program to get handlers
        GLES20.glUseProgram(MyProgram);

        //update position
        vertexBuffer.put(coords);
        vertexBuffer.position(0);

        //update to image texture of this nodes instance
        imageBuffer.put(imageVertex);
        imageBuffer.position(0);

        //bind texture to be drawn
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D,texturenames[0]);

        //get handle to vertex shader vPosition member
        int myPositionHandle = GLES20.glGetAttribLocation(MyProgram, "vPosition");

        //Enable a handle to the triangle vertices
        GLES20.glEnableVertexAttribArray(myPositionHandle);

        //Prepare the triangle coordinate data
        GLES20.glVertexAttribPointer(myPositionHandle,COORDS_PER_VERTEX,
                GLES20.GL_FLOAT,false,
                0, vertexBuffer);

        //get handle to texture coordinates location
        int mTexCoordLoc = GLES20.glGetAttribLocation(MyProgram, "a_texCoord");

        GLES20.glEnableVertexAttribArray (mTexCoordLoc);

        GLES20.glVertexAttribPointer(mTexCoordLoc, 2, GLES20.GL_FLOAT, false, 0, imageBuffer);

        //get handler to shapes's transformation matrix
        int myMVPMatrixHandle = GLES20.glGetUniformLocation(MyProgram, "uMVPMatrix");

        // Apply the projection and view transformation
        GLES20.glUniformMatrix4fv(myMVPMatrixHandle, 1, false, mvpMatrix, 0);

        //get handle to textures locations
        int mSamplerLoc = GLES20.glGetUniformLocation(MyProgram, "s_texture");

        //set the sampler texture unit to 0,where we have saved the texture
        GLES20.glUniform1f(mSamplerLoc,0);

        //Draw the square
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, drawOrder.length,
                GLES20.GL_UNSIGNED_SHORT, drawListBuffer);

        //Disable vertex array
        GLES20.glDisableVertexAttribArray(myPositionHandle);
    }

    public void moveDown(float amount){
        centerPoint[1] -= (height * amount);
        createNodeItem();
    }

    public void moveUp(float amount){
        centerPoint[1] += (height* amount);
        createNodeItem();
    }
    public void moveRight(float amount){
        centerPoint[0] -= (height*amount);
        createNodeItem();
    }
    public void moveLeft(float amount){
        centerPoint[0] += (height * amount);
        createNodeItem();
    }

    public float getHeight(){
        return height;
    }
}