package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Richard on 01/03/2015.
 */

public class ArrayIntro extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrayintro);
        setUPTutorial();
    }

    private void setUPTutorial(){
        //this method sets up the page to include information about the tutorial.

        String topic = this.getResources().getString(R.string.arrays_intro_heading);
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(topic);

        String intro = this.getResources().getString(R.string.arrays_intro_paragraph);
        textViewToChange = (TextView) findViewById(R.id.arrayIntro);
        textViewToChange.setText(intro);



    }
}
