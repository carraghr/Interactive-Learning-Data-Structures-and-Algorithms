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
public class ArrayBubbleSort extends Activity{

    @Override
    //process to start when activity is created.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        setContentView(R.layout.five_input_page);
        Button bubbleSortButton = (Button) findViewById(R.id.submit_five);

        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        //set up back button to go to last topic
        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayBubbleSort.this, ArraySelectSort.class);
                ArrayBubbleSort.this.startActivity(intent);
            }
        });

        //set up back button to go to next topic
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayBubbleSort.this, ArrayLinearSearch.class);
                ArrayBubbleSort.this.startActivity(intent);
            }
        });

        //set up diagram submit button
        bubbleSortButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get users input and convert it to string array for diagram.
                EditText one_input = (EditText) findViewById(R.id.input_one);
                EditText two_input = (EditText) findViewById(R.id.input_two);
                EditText three_input = (EditText) findViewById(R.id.input_three);
                EditText four_input = (EditText) findViewById(R.id.input_four);
                EditText five_input = (EditText) findViewById(R.id.input_five);

                String [] values = new String[]{
                        one_input.getText().toString(),
                        two_input.getText().toString(),
                        three_input.getText().toString(),
                        four_input.getText().toString(),
                        five_input.getText().toString()};

                values = InputControls.sortedValues(values);

                //ensure user has inputted values to sort
                if(values.length == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input values to sort"
                            , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    //pass values to be sorted to new activity via a bundle
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("values",values);
                    //create new intent to start and add bundle to it
                    Intent intent = new Intent(ArrayBubbleSort.this, ArrayBubbleSortSurfaceActivity.class);
                    intent.putExtras(bundle);
                    //start new activity
                    ArrayBubbleSort.this.startActivity(intent);
                }
            }
        });

        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_bubble_sort);

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(R.string.array_bubble_sort_paragraph);
    }
}

