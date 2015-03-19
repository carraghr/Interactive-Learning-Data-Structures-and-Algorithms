package interactivelearning.datastructuresandalgorthims;

import android.content.Context;

/**
 * Created on 19/03/2015.
 */
public class Node {
    private NodeItem item;
    private NodeRef reference;
    private float [] centerPoint;
    private String fileName;
    Context context;
    boolean hasref;

    Node(float[] centerPoint,String fileName,Context context,boolean hasRef){
        this.centerPoint = centerPoint;
        this.fileName = fileName;
        this.hasref = hasRef;
        setUpNode();
    }

    private void setUpNode(){
        item = new NodeItem(new float[]{centerPoint[0] + 0.06f,centerPoint[1],0.00f},context,fileName);
        reference = new NodeRef(new float[]{centerPoint[0] - 0.015f,centerPoint[1],0.00f},context,hasref);
    }

    public void draw(float[] mvpMatrix){
        item.draw(mvpMatrix);
        reference.draw(mvpMatrix);
    }

    public void changeRef(boolean refType){
        reference.change(refType);
    }
}
