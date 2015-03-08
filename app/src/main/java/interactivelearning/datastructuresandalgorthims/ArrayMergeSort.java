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
public class ArrayMergeSort extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arraymergesort);
        Button selectSortButton = (Button) findViewById(R.id.array_linear_search_button);

        selectSortButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "You sure showed that select sort button", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(ArrayMergeSort.this,SurfaceActivity.class);
                ArrayMergeSort.this.startActivity(intent);
            }
        });

        //TODO write function to out new strings for declaring arrays of set length.
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_merge_sort);

        textViewToChange = (TextView) findViewById(R.id.array_text);
        textViewToChange.setText(R.string.array_merge_sort_para);
    }
}
