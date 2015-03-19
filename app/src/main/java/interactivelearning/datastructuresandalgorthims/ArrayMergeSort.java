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
public class ArrayMergeSort extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_input_page);
        Button mergeSortButton = (Button) findViewById(R.id.submit_five);
        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayMergeSort.this, ArrayBubbleSort.class);
                ArrayMergeSort.this.startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayMergeSort.this, ArrayLinearSearch.class);
                ArrayMergeSort.this.startActivity(intent);
            }
        });

        mergeSortButton.setOnClickListener(new View.OnClickListener() {
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
                    Intent intent = new Intent(ArrayMergeSort.this, ArrayMergeSortSurfaceActivity.class);
                    intent.putExtras(bundle);
                    ArrayMergeSort.this.startActivity(intent);

                }
            }
        });

        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_merge_sort);

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(R.string.array_merge_sort_paragraph);
    }
}
