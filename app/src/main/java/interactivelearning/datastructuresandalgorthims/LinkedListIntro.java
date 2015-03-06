package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Jessica on 06/03/2015.
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

        String topic = this.getResources().getString(R.string.linkedlist_intro);
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(topic);

        String intro = this.getResources().getString(R.string.linkedlist_intro_para);
        textViewToChange = (TextView) findViewById(R.id.linkedListIntro);
        textViewToChange.setText(intro);
    }
}

