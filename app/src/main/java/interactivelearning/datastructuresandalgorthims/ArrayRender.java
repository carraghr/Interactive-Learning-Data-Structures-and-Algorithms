package interactivelearning.datastructuresandalgorthims;

import android.content.Context;

/**
 * Created on 21/03/2015.
 */
public class ArrayRender {

    public Context context;
    public int numberOfElements;
    public String [] fileNames;
    public Square[] squares;

    ArrayRender(Context context, int numberOfElements, String[] fileNames){

        this.context = context;
        this.numberOfElements = numberOfElements;
        this.fileNames = fileNames;

        squares = new Square[this.numberOfElements];
    }

    public void setUpArray(){
        float offset = 0.00f;
        int left,right;
        float radius = Square.radius;

        if(numberOfElements%2!=0){
            squares[numberOfElements/2] = new Square(new float[]{0.0f,0.0f},context,fileNames[numberOfElements/2]);
            offset+=2*radius;
            right =  numberOfElements/2+1;
        }
        else{
            offset+=radius;
            right =  numberOfElements/2;
        }

        for(left = numberOfElements/2 - 1; right<numberOfElements && left >-1; right++, left--){
            squares[right] = new Square(new float[]{ 0.0f - offset ,0.0f},context,fileNames[right]);
            squares[left] = new Square(new float[]{ 0.0f + offset,0.0f},context,fileNames[left]);
            offset+=(2*radius);
        }
    }

    public void drawArray(float[] mvpMatrix){
        for (int i = 0; i < numberOfElements; i++){
            squares[i].draw(mvpMatrix);
        }
    }


}
