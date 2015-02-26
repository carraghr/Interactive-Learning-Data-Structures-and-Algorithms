package interactivelearning.datastructuresandalgorthims;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Created by Richard on 26/02/2015.
 */
public class TopicFileHandler {
    private final String fileName = "topics.dat";
    private int numberOfTopics ;
    private long lengthOfTopicName = 32; //length 30 with 2 addital for file format
    private RandomAccessFile file;
    private Topic [] topics;

    TopicFileHandler() throws FileNotFoundException, IOException{

        openFile();

        numberOfTopics = file.readInt();
        topics = new Topic[numberOfTopics];

        getTopics();

    }

    private boolean openFile() throws FileNotFoundException{

        file = new RandomAccessFile(fileName,"r");
        return true;

    }


    private void getTopics() throws IOException{

        String name;
        int numOfSubTopics;
        String [] subtopics = null;

        for(int i=0;i<numberOfTopics;i++){
            name=file.readUTF().trim();
            numOfSubTopics = file.readInt();
            if(numOfSubTopics > 0){
                subtopics = new String[numOfSubTopics];
                for(int j=0; j < numOfSubTopics; j++ ){
                    subtopics[j] = file.readUTF();
                }
            }
            else{
                subtopics = null;
            }
            topics[i] = new Topic( name, numOfSubTopics, subtopics);
        }

    }

    public String [] getTopicNames() throws IOException{

        String [] names =new String [topics.length];
        for(int i = 0; i< names.length;i++){
            names[i]=topics[i].getTopicName();
        }
        return names;
    }

    public String [] getSubtopicNames(String topicName) throws IOException{

        for(int i=0;i<topics.length;i++){
            String temp =topics[i].getTopicName();
            if(temp.equals(topicName)){
                return topics[i].getSubtopics();
            }
        }
        return null;
    }

    public void close() throws IOException {
        file.close();
    }

    public static void main(String [] args){

        try{
            TopicFileHandler handle= new TopicFileHandler();

            String [] a = handle.getSubtopicNames("Bag");
            for(int i=0;i<a.length;i++){
                System.out.println(a[i]);
            }

        }catch(Exception e){
            System.out.println("main fail");

        }
    }
}
