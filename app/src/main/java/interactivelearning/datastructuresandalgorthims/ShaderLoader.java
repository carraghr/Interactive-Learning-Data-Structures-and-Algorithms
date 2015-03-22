package interactivelearning.datastructuresandalgorthims;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created on 04/03/2015.
 */
public class ShaderLoader{

    private static final String TAG = "ShaderLoader";

    //shader code for coords and textures
    public final static String VertexShaderCode =
            //Is necessary for drawing
            "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "attribute vec2 a_texCoord;" +
                    "varying vec2 v_texCoord;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;"+
                    "  v_texCoord = a_texCoord;" +
                    "}";

    //shader code for coords and textures
    public final static String FragmentShaderCode =
            //Is necessary for image
            "precision mediump float;" +
                    "varying vec2 v_texCoord;" +
                    "uniform sampler2D s_texture;" +
                    "void main() {" +
                    "  gl_FragColor = texture2D( s_texture, v_texCoord );" +
                    "}";

    //shader code for coords and color display
    public final static  String VertexShaderCodeColor =
                    "uniform mat4 uMVPMatrix;" +
                    "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = uMVPMatrix * vPosition;" +
                    "}";

    public final static String FragmentShaderCodeColor =
                    "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";

    //shader code for coords and color display
    public static int loadShader(int type,String shaderCode){

        // create a vertex shader type (GLES20.GL_VERTEX_SHADER)
        // or a fragment shader type (GLES20.GL_FRAGMENT_SHADER)

        int shader = GLES20.glCreateShader(type);
        ShaderLoader.checkGlError("glCreateShader");
        GLES20.glShaderSource(shader,shaderCode);
        ShaderLoader.checkGlError("glShaderSource");
        GLES20.glCompileShader(shader);
        ShaderLoader.checkGlError("glCompileShader");

        return shader;
    }

    public static void checkGlError(String glOperation){
        int error;

        while ((error = GLES20.glGetError()) != GLES20.GL_NO_ERROR) {
            Log.e(TAG, glOperation + ": glError " + error);
            throw new RuntimeException(glOperation + ": glError " + error);
        }
    }
}