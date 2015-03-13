//package interactivelearning.datastructuresandalgorthims;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Scanner;

/**
 * Created by Richard on 26/02/2015.
 */
public class Txt_to_Dat {

    public static void main(String [] args){
        String line;
        try{
            
            Scanner brTxt = new Scanner(new File("topics.txt"));

            RandomAccessFile topicFile = new RandomAccessFile("topics.dat","rw");
            if(brTxt.hasNext()){
                line = brTxt.nextLine();
                topicFile.writeInt(Integer.parseInt(line));
            }

            while(brTxt.hasNext()){
                line = brTxt.nextLine();
                String [] lines = line.split(" ");

                lines[0] = lines[0].replace("_"," ");
                lines[0] = String.format("%-30s",lines[0]);
                topicFile.writeUTF(lines[0]);

                topicFile.writeInt(Integer.parseInt(lines[1]));

                int end =Integer.parseInt(lines[1]);
                System.out.println(lines[0]+" "+ lines[1]);

                for(int i=0;i<end;i++){
                    line = brTxt.nextLine();
					line = line.replace("_"," ");
                    line = String.format("%-30s",line);
                    topicFile.writeUTF(line);
                    System.out.println(line);
                }
            }
            brTxt.close();
            topicFile.close();

        }catch(Exception e){
            System.out.println("Error");
        }
    }
}
