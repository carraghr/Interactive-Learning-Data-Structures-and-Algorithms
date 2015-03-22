package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created on 10/03/2015.
 */
public class LinkedListRotation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotation);
        setUPTutorial();
    }

    private void setUPTutorial(){
        //this method sets up the page to include information about the tutorial.

        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_rotation));

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_rotation_paragraph));

        Button previous = (Button) findViewById(R.id.previous);
        Button submit = (Button) findViewById(R.id.submit_one);

        //set up previous button to go to last topic
        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListRotation.this, LinkedListRemoval.class);
                LinkedListRotation.this.startActivity(intent);
            }
        });

        //set up submit button to start diagram.
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get user input and change to string
                EditText input_one = (EditText) findViewById(R.id.input_box_one);
                String rotations = input_one.getText().toString();

                //check if input is not empty
                if (rotations.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input a number of rotations you want to see."
                            , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {

                    //create a bundle to be passed to the new activity
                    Bundle bundle = new Bundle();
                    bundle.putString("numberOfRotates", rotations);
                    //create a new intent to start the activity and pass bundle.
                    Intent intent = new Intent(LinkedListRotation.this, LinkedListRotationSurfaceActivity.class);
                    intent.putExtras(bundle);
                    //start activity
                    LinkedListRotation.this.startActivity(intent);

                }
            }
        });

    }
}
