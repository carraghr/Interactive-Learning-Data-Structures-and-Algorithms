package interactivelearning.datastructuresandalgorthims;

/**
 * Created by Richard on 26/02/2015.
 */
public class Topics {

    private Topic[] topics;

    Topics(Topic[] topics) {
        this.topics = topics;
    }

    public String[] getTopicNames(){
        String[] names = new String[topics.length];
        for(int i=0; i < topics.length; i++){
            names[i] = topics[i].getTopicName();
        }
        return names;
    }
}
