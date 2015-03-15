package interactivelearning.datastructuresandalgorthims;

import android.opengl.GLES20;
import android.util.Log;

/**
 * Created by Richard on 04/03/2015.
 */
public class ShaderLoader{

    private static final String TAG = "ShaderLoader";

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
}