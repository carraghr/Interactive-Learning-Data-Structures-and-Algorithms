package interactivelearning.datastructuresandalgorthims;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/**
 * Created on 19/03/2015.
 */
public class ArrayBinarySearchRenderer implements GLSurfaceView.Renderer {

    private Square[] squares;
    private int numberOfSquares;
    private String[] fileNames;
    private Square searchFor;
    private String searchForImage;

    Context context;

    private final float[] mMVPMatrix = new float[16];
    private final float[] mProjectionMatrix = new float[16];
    private final float[] mViewMatrix = new float[16];

    ArrayBinarySearchRenderer(Context context, int numberOfSquares, String[] fileNames, String searchForImage) {

        super();
        this.context = context;

        this.fileNames = fileNames;

        this.numberOfSquares = numberOfSquares;

        squares = new Square[this.numberOfSquares];

        this.searchForImage = searchForImage;

    }

    @Override//to be called on set up of renderer
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame colour - blue
        GLES20.glClearColor(0.24f, 0.522f, 0.863f, 0.0f);

        // set up array
        setUpSquares();
        //set up value to be searched for.
        addSearchFor();
    }

    @Override //to be called when user changes device rotation
    public void onSurfaceChanged(GL10 gl, int width, int height) {

        GLES20.glViewport(0, 0, width, height);

        float ratio = (float) width / height;

        Matrix.frustumM(mProjectionMatrix, 0, -ratio, ratio, -1, 1, 3, 7);

    }

    @Override//to be called when render requested
    public void onDrawFrame(GL10 gl) {

        //Draw background color
        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT | GLES20.GL_DEPTH_BUFFER_BIT);

        //Set the camera position (View Matrix)
        Matrix.setLookAtM(mViewMatrix, 0, 0, 0, -3, 0f, 0f, 0f, 0f, 1.0f, 0.0f);

        // Calculate the projection and view transformation
        Matrix.multiplyMM(mMVPMatrix, 0, mProjectionMatrix, 0, mViewMatrix, 0);

        //draw each square.
        for (int i = 0; i < numberOfSquares; i++) {
            squares[i].draw(mMVPMatrix);
        }
        //draw what is to be searched for.
        searchFor.draw(mMVPMatrix);

    }

    public void setUpSquares() {

        // space between each square
        float offset = 0.001f;

        //left and right from the center
        int left, right;

        //get the radius of a square ie length of center to one side.
        float radius = Square.getRadius();

        //if odd number of squares first is at center point of screen
        if (numberOfSquares % 2 != 0) {
            //create square at center
            squares[numberOfSquares / 2] = new Square(new float[]{0.0f, 0.0f}, context, fileNames[numberOfSquares / 2]);
            //add to offset
            offset += 2 * radius;
            //move right pointer over to next square location.
            right = numberOfSquares / 2 + 1;

        }
        else {
            //else start with center touching two sides.
            offset += radius;
            right = numberOfSquares / 2;
        }
        //loop through squares an crate each one from the center outwards.
        for (left = numberOfSquares / 2 - 1; right < numberOfSquares && left > -1; right++, left--) {
            squares[right] = new Square(new float[]{0.0f - offset, 0.0f}, context, fileNames[right]);
            squares[left] = new Square(new float[]{0.0f + offset, 0.0f}, context, fileNames[left]);
            offset += (2 * radius) + 0.002f;
        }
    }

    public void highLight(int place) {
        //move square i up by its radius twice.
        squares[place].moveUp(2 * Square.getRadius());
    }

    public void removeHighLight(int place) {
        //move square i down by its radius twice.
        squares[place].moveDown(Square.getRadius() * 2);
    }

    public void addSearchFor() {
        //add search value to the screen over the first element in the array.
        float[] startPoint = squares[0].getTopCenterPoint();
        startPoint[1] += (Square.getRadius() * 5) + 0.005f;
        searchFor = new Square(startPoint, context, searchForImage);
    }
}
