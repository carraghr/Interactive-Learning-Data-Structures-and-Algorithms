package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created on 15/03/2015.
 */
public class CodeSelectSort extends Activity {

    @Override//process to start when activity is created.
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI(){
        //this method sets up the page to include information about the tutorial.
        setContentView(R.layout.text_with_no_input);
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.code_select_sort));

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(this.getResources().getString(R.string.code_select_sort_paragraph));

        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        //set up back button to go to last topic
        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CodeSelectSort.this, CodeBubbleSort.class);
                CodeSelectSort.this.startActivity(intent);
            }
        });

        //set up back button to go to next topic
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CodeSelectSort.this, CodeLinearSearch.class);
                CodeSelectSort.this.startActivity(intent);
            }
        });
    }
}