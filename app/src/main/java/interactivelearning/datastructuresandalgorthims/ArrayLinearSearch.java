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
public class ArrayLinearSearch extends Activity {

    @Override //called on activates creation
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI(){

        setContentView(R.layout.one_input_page);
        Button linearSearchButton = (Button) findViewById(R.id.submit_one);
        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        //set up previous button to go to last topic.
        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayLinearSearch.this, ArrayBubbleSort.class);
                ArrayLinearSearch.this.startActivity(intent);
            }
        });

        //set up next button to go to next topic
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayLinearSearch.this, ArrayBinarySearch.class);
                ArrayLinearSearch.this.startActivity(intent);
            }
        });

        //set up button to read input and start diagram
        linearSearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //get input
                EditText input = (EditText) findViewById(R.id.input_box_one);
                String value = input.getText().toString();
                //ensure there is input to use
                if(value.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(),
                                                    "Please enter a value to be searched for",
                                                        Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {

                    //pass input to new activity
                    Bundle bundle = new Bundle();
                    bundle.putString("value", value);

                    //start new activity and apply input.
                    Intent intent = new Intent(ArrayLinearSearch.this, ArrayLinearSearchSurfaceActivity.class);
                    intent.putExtras(bundle);
                    ArrayLinearSearch.this.startActivity(intent);
                }
            }
        });

        //set topic header
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_linear_search);

        //set text
        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(R.string.array_linear_search_paragraph);
    }
}
