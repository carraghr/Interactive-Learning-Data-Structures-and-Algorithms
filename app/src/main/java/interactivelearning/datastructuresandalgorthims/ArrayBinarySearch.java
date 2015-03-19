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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI(){
    setContentView(R.layout.binary_search);
    Button binarySearchButton = (Button) findViewById(R.id.submit_one);
    Button previous = (Button) findViewById(R.id.previous);

    previous.setOnClickListener(new View.OnClickListener() {
        public void onClick(View v) {
            Intent intent = new Intent(ArrayBinarySearch.this, ArrayLinearSearch.class);
            ArrayBinarySearch.this.startActivity(intent);
        }
    });

        binarySearchButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            EditText input = (EditText) findViewById(R.id.input_box_one);
            String value = input.getText().toString();
            if(value.equals("")) {
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Please enter a value to be searched for",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
            else {

                Bundle bundle = new Bundle();
                bundle.putString("value", value);

                Intent intent = new Intent(ArrayBinarySearch.this, ArrayBinarySearchSurfaceActivity.class);
                intent.putExtras(bundle);
                ArrayBinarySearch.this.startActivity(intent);
            }

        }
    });

    TextView textViewToChange = (TextView) findViewById(R.id.topic);
    textViewToChange.setText(R.string.array_binary_search);

    textViewToChange = (TextView) findViewById(R.id.first_text_block);
    textViewToChange.setText(R.string.array_binary_search_paragraph);
    }
}
