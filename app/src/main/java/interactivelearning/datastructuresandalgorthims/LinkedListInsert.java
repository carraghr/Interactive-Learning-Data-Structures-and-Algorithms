package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created on 10/03/2015.
 */
public class LinkedListInsert extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI(){
        //this method sets up the page to include information about the tutorial.
        setContentView(R.layout.two_input_page);
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_insertion));

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_insertion_paragraph));

        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListInsert.this, LinkedListDeclaration.class);
                LinkedListInsert.this.startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListInsert.this, LinkedListRemoval.class);
                LinkedListInsert.this.startActivity(intent);
            }
        });
    }
}
