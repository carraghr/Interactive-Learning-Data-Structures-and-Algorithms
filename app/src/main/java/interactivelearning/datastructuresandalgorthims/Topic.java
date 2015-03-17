package interactivelearning.datastructuresandalgorthims;

/**
 * Created on 26/02/2015.
 */
public class Topic{

    private String name;
    private int numOfSubtopics;

    private String [] subtopics;

    public Topic(String name,int num_of_subtopics,String[] subtopics){
        this.name = name;
        this.numOfSubtopics = numOfSubtopics;
        this.subtopics = subtopics;
    }

    public String getTopicName(){
        return name;
    }

    public String [] getSubtopics(){
        return subtopics;
    }

}
