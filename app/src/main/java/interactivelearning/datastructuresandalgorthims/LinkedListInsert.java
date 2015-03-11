package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Richard on 10/03/2015.
 */
public class LinkedListInsert extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linkedlistintro);
        setUPTutorial();
    }

    private void setUPTutorial(){
        //this method sets up the page to include information about the tutorial.

        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.linkedlist_insertion));

        textViewToChange = (TextView) findViewById(R.id.linkedListIntroPara1);
        textViewToChange.setText(this.getResources().getString(R.string.linkedlist_insertion_para));
    }
}
