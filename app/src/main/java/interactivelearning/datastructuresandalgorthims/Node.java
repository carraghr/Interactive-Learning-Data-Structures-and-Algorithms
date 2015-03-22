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
    boolean hasRef;

    Node(float[] centerPoint,String fileName,Context context,boolean hasRef){
        this.centerPoint = centerPoint;
        this.fileName = fileName;
        this.hasRef = hasRef;
        this.context = context;
        setUpNode();
    }

    private void setUpNode(){
        //set up item node and ref node
        item = new NodeItem(new float[]{centerPoint[0] + 0.06f,centerPoint[1],0.00f},context,fileName);
        reference = new NodeRef(new float[]{centerPoint[0] - 0.015f,centerPoint[1],0.00f},hasRef);
    }

    public void draw(float[] mvpMatrix){
        //call nodes draw methods
        item.draw(mvpMatrix);
        reference.draw(mvpMatrix);
    }

    public void changeRef(){
        reference.change();
    }

    public float [] getCenterPoint(){
        return centerPoint;
    }
    public float getHeight(){
        return item.getHeight();
    }

    public void moveUp(){
        item.moveUp(2);
        reference.moveUp(2);
    }

    public void moveDown(){
        item.moveDown(2);
        reference.moveDown(2);
    }

    public void moveLeft(){
        item.moveLeft(2);
        reference.moveLeft(2);
    }

    public void moveRight(){
        item.moveRight(2);
        reference.moveRight(2);
    }
}