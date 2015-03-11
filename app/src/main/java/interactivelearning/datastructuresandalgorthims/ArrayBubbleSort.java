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
public class ArrayBubbleSort extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.five_input_page);
        Button selectSortButton = (Button) findViewById(R.id.submit_five);

        selectSortButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "You sure showed that bubble sort button", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(ArrayBubbleSort.this,SurfaceActivity.class);
                ArrayBubbleSort.this.startActivity(intent);
            }
        });

        //TODO write function to out new strings for declaring arrays of set length.
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_bubble_sort);

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(R.string.array_bubble_sort_para);
    }
}
