package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Jessica on 06/03/2015.
 * Edited by Richard on 7/3/15 - added text and image from resources I created for the intro to LinkedList
 */

public class LinkedListIntro extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linkedlistintro);
        setUPTutorial();
    }

    private void setUPTutorial(){
        //this method sets up the page to include information about the tutorial.

        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.linkedlist_intro));

        textViewToChange = (TextView) findViewById(R.id.linkedListIntroPara1);
        textViewToChange.setText(this.getResources().getString(R.string.linkedlist_intro_para1));

        ImageView image = (ImageView) findViewById(R.id.image);
        image.setImageResource(R.mipmap.singly_linked_node);

        textViewToChange = (TextView) findViewById(R.id.linkedListIntroPara2);
        textViewToChange.setText(this.getResources().getString(R.string.linkedlist_intro_para2));

    }
}

