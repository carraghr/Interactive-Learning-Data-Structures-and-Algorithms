package interactivelearning.datastructuresandalgorthims;

import java.util.ArrayList;

/**
 * Created on 12/03/2015.
 */
public class InputControls {

    public static String [] sortedValues(String [] values){
        //takes in a string array and gets rid of any values that are empty strings
        //returns one with no empty strings

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
        //takes in string array with values that can be shown as images
        //returns array of image names.
        String [] files = new String[values.length];

        for(int i=0; i<values.length;i++){
            files[i] = "number_" + values[i];
        }
        return files;
    }

    public static String addImageName(String value){
        //takes in string with a value that can be shown as an image
        //returns string of image name.
        return "number_" + value;
    }
}
