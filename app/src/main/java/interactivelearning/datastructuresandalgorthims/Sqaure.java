package interactivelearning.datastructuresandalgorthims;

import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

/**
 * Created by Richard on 04/03/2015.
 */
public class Sqaure{

    //respo
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
    private int MyMVPMatrixHandle;

    //number of coordinate per vertext in this array
    static final int Coords_PER_VERTEX
}
