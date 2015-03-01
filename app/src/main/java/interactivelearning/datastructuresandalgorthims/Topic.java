package interactivelearning.datastructuresandalgorthims;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Richard on 26/02/2015.
 */
public class Topic implements Parcelable{

    private String name;
    private int numOfSubtopics;

    private String [] subtopics;

    public Topic(String name,int num_of_subtopics,String[] subtopics){
        this.name=name;
        this.numOfSubtopics=numOfSubtopics;
        this.subtopics=subtopics;
    }

    public Topic(Parcel in) {

        String[] data = new String[30];
        in.readStringArray(data);
        this.name=data[0];
        this.numOfSubtopics = Integer.parseInt(data[1]);
        this.subtopics = new String[this.numOfSubtopics];
        for(int i=2; ( i < data.length) && (i-2 <this.numOfSubtopics); i++){
            this.subtopics[i-2] = data[i];
        }

    }

    public String getTopicName(){
        return name;
    }

    public int getNumOfSubtopics(){
        return numOfSubtopics;
    }

    public String [] getSubtopics(){
        return subtopics;
    }

    //parcel part

    @Override
    public int describeContents(){
        //TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags){
        //TODO Auto-generated method stub
        String [] data = new String[ 2 + this.subtopics.length ];
        data[0] = name;
        data[1] = ""+numOfSubtopics;
        for(int i = 2; i<data.length;i++){
            data[i] = subtopics[i-2];
        }
        dest.writeStringArray(data);
    }

    public Topic createFormParcel(Parcel source){
        return new Topic(source);
    }

    public Topic [] newArray(int size){
        return new Topic[size];
    }
}
