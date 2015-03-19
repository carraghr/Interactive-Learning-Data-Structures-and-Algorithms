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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        setContentView(R.layout.five_input_page);
        Button bubbleSortButton = (Button) findViewById(R.id.submit_five);

        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayBubbleSort.this, ArraySelectSort.class);
                ArrayBubbleSort.this.startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayBubbleSort.this, ArrayMergeSort.class);
                ArrayBubbleSort.this.startActivity(intent);
            }
        });

        bubbleSortButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText one_input = (EditText) findViewById(R.id.input_one);
                EditText two_input = (EditText) findViewById(R.id.input_two);
                EditText three_input = (EditText) findViewById(R.id.input_three);
                EditText four_input = (EditText) findViewById(R.id.input_four);
                EditText five_input = (EditText) findViewById(R.id.input_five);

                String [] values = new String[]{one_input.getText().toString(),
                        two_input.getText().toString(),
                        three_input.getText().toString(),
                        four_input.getText().toString(),
                        five_input.getText().toString()};
                values = InputControls.sortedValues(values);

                if(values.length == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input values to sort"
                            , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putStringArray("values",values);
                    Intent intent = new Intent(ArrayBubbleSort.this, ArrayBubbleSortSurfaceActivity.class);
                    intent.putExtras(bundle);
                    ArrayBubbleSort.this.startActivity(intent);

                }
            }
        });

        //TODO write function to out new strings for declaring arrays of set length.
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_bubble_sort);

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(R.string.array_bubble_sort_paragraph);
    }
}

