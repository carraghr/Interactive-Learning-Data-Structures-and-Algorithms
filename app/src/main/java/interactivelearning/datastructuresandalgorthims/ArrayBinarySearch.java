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
 * Created on 08/03/2015.
 */
public class ArrayBinarySearch extends Activity{

    @Override
    //process to start when activity is created.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI(){

        setContentView(R.layout.binary_search);
        Button binarySearchButton = (Button) findViewById(R.id.submit_one);
        Button previous = (Button) findViewById(R.id.previous);


        //set up back button to go to last topic
        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayBinarySearch.this, ArrayLinearSearch.class);
                ArrayBinarySearch.this.startActivity(intent);
            }
        });

        //set up diagram submit button
        binarySearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get users input and convert it to string
                EditText input = (EditText) findViewById(R.id.input_box_one);
                String value = input.getText().toString();
                //ensure user has inputted a value to search for.
                if(value.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                            "Please enter a value to be searched for",
                            Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {

                    //create bundle for activity to pass to new activity
                    Bundle bundle = new Bundle();
                    //add value to be searched for
                    bundle.putString("value", value);
                    //create a new intent to start activity and pass it the bundle.
                    Intent intent = new Intent(ArrayBinarySearch.this, ArrayBinarySearchSurfaceActivity.class);
                    intent.putExtras(bundle);
                    //start new activity.
                    ArrayBinarySearch.this.startActivity(intent);
                }

            }
        });

        //set up topic header.
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_binary_search);
        //set up text for user
        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(R.string.array_binary_search_paragraph);
    }
}