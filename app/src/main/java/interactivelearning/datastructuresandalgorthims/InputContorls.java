package interactivelearning.datastructuresandalgorthims;

import java.util.ArrayList;

/**
 * Created by Richard on 12/03/2015.
 */
public class InputContorls {

    public static String [] sortedValues(String [] values){

        ArrayList<String> ordered= new ArrayList<>();

        for(int i=0; i < values.length;i++){
            if(!(values[i].equals(""))){
                ordered.add(values[i]);
            }
        }

        String[] ret = new String[ordered.size()];
        ret=ordered.toArray(ret);
        return ret;
    }

    public static String [] addImageNames(String [] values){
        String [] files = new String[values.length];

        for(int i=0; i<values.length;i++){
            files[i] = "number_"+values[i];
        }
        return files;
    }
}
