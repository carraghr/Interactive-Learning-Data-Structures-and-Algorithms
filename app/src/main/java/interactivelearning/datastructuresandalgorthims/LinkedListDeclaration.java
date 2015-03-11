package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Jessica on 06/03/2015. - Class name.
 * Edited by Richard on 7/3/15 - now extends Activity
 *                             -
 */
public class LinkedListDeclaration extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linkedlistintro);
        setUPTutorial();
    }

    private void setUPTutorial(){
        //this method sets up the page to include information about the tutorial.

        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_declaration));

        textViewToChange = (TextView) findViewById(R.id.linkedListIntroPara1);
        textViewToChange.setText(this.getResources().getString(R.string.linkedlist_declaration_para));
    }
}