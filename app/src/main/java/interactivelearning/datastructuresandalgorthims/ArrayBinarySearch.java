package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jessica on 08/03/2015.
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
            Toast toast = Toast.makeText(getApplicationContext(), "You sure showed that select sort button", Toast.LENGTH_SHORT);
            toast.show();

           // Intent intent = new Intent(ArrayBinarySearch.this,SurfaceActivity.class);
           // ArrayBinarySearch.this.startActivity(intent);
        }
    });

    //TODO write function to out new strings for declaring arrays of set length.
    TextView textViewToChange = (TextView) findViewById(R.id.topic);
    textViewToChange.setText(R.string.array_binary_search);

    textViewToChange = (TextView) findViewById(R.id.first_text_block);
    textViewToChange.setText(R.string.array_binary_search_paragraph);
    }
}
