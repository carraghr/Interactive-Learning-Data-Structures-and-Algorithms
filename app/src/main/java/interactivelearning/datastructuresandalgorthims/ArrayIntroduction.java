package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Richard on 01/03/2015.
 */

public class ArrayIntroduction extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arrayintroduction);
        setUPTutorial();
    }

    private void setUPTutorial(){
        //this method sets up the page to include information about the tutorial.

        String topic = this.getResources().getString(R.string.array_introduction);
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(topic);

        String intro = this.getResources().getString(R.string.arrays_intro_paragraph);
        textViewToChange = (TextView) findViewById(R.id.array_text);
        textViewToChange.setText(intro);
    }
}
