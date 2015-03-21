package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created on 06/03/2015.
 */
public class LinkedListDeclaration extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        //this method sets up the page to include information about the tutorial.
        setContentView(R.layout.text_with_no_input);
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_declaration));

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_declaration_paragraph));

        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        //set up back button to go to last topic
        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListDeclaration.this, LinkedListIntro.class);
                LinkedListDeclaration.this.startActivity(intent);
            }
        });

        //set up back button to go to next topic
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListDeclaration.this, LinkedListInsert.class);
                LinkedListDeclaration.this.startActivity(intent);
            }
        });
    }
}