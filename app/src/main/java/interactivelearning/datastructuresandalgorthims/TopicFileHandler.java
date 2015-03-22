package interactivelearning.datastructuresandalgorthims;

import android.content.Context;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * Created on 26/02/2015.
 */
public class TopicFileHandler {
    public int numberOfTopics ;
    private DataInputStream file;
    private Topics topics;
    private Context con;

    TopicFileHandler(Context con) throws IOException{

        this.con = con;
        openFile();

        numberOfTopics = file.readInt();

    }

    private void openFile() throws IOException {

        String fileName = "topics.dat";
        //open file
        file = new DataInputStream(con.getAssets().open(fileName));

    }

    public Topics getTopics() throws IOException{

        topics = new Topics(numberOfTopics);
        String name;
        int numOfSubTopics;
        String [] subtopics ;

        for(int i=0;i<numberOfTopics;i++){
            //read topic name and remove padding
            name=file.readUTF().trim();
            //get number of subtopics
            numOfSubTopics = file.readInt();
            //read subtopics in
            if(numOfSubTopics > 0){
                subtopics = new String[numOfSubTopics];
                for(int j=0; j < numOfSubTopics; j++ ){
                    subtopics[j] = file.readUTF().trim();
                }
            }
            else{
                subtopics = null;
            }
            //create a topic and add it to topics
            topics.putTopic( new Topic( name, numOfSubTopics, subtopics));
        }
        return topics;
    }

    public void close() throws IOException {
        file.close();
    }

}
