package interactivelearning.datastructuresandalgorthims;

import android.content.Context;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created by Richard on 26/02/2015.
 */
public class TopicFileHandler {
    public int numberOfTopics ;
    private DataInputStream file;
    private Topic [] topics;
    private Context con;

    TopicFileHandler(Context con) throws IOException{

        this.con = con;
        openFile();

        numberOfTopics = file.readInt();

        topics = new Topic[numberOfTopics];

    }

    private void openFile() throws IOException {

        String fileName = "topics.dat";
        //file = new DataInputStream(con.getAssets().open(fileName));
        file = new DataInputStream(con.getAssets().open(fileName));

    }

    public Topic [] getTopics() throws IOException{

        String name;
        int numOfSubTopics;
        String [] subtopics ;

        for(int i=0;i<numberOfTopics;i++){
            name=file.readUTF().trim();
            numOfSubTopics = file.readInt();
            if(numOfSubTopics > 0){
                subtopics = new String[numOfSubTopics];
                for(int j=0; j < numOfSubTopics; j++ ){
                    subtopics[j] = file.readUTF().trim();
                }
            }
            else{
                subtopics = null;
            }
            topics[i] = new Topic( name, numOfSubTopics, subtopics);
        }
        return topics;
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

}
