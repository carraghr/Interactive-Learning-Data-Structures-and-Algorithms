package interactivelearning.datastructuresandalgorthims;

/**
 * Created on 26/02/2015.
 */
public class Topics{

    private Topic[] topics;
    private int numberOfTopics = 0;

    Topics(int numberOfTopics){ topics = new Topic[numberOfTopics];}

    public String[] getTopicNames(){
        String[] names = new String[topics.length];
        for(int i=0; i < topics.length; i++){
            names[i] = topics[i].getTopicName();
        }
        return names;
    }

    public Topic getTopic(String name){
        for(int i=0;i<topics.length;i++ ){
            if(topics[i].getTopicName().equals(name)){
                return topics[i];
            }
        }
        return null;
    }

    public boolean putTopic(Topic topic){
        if( numberOfTopics < topics.length ){
            topics[numberOfTopics]=topic;
            numberOfTopics++;
            return true;
        }
        return false;
    }
}
