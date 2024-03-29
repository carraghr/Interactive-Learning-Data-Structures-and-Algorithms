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
        Button submit = (Button) findViewById(R.id.submit_two);

        //set up back button to go to last topic
        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListInsert.this, LinkedListDeclaration.class);
                LinkedListInsert.this.startActivity(intent);
            }
        });

        //set up back button to go to next topic
        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListInsert.this, LinkedListRemoval.class);
                LinkedListInsert.this.startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

                //get user input and change to strings
                EditText input_one = (EditText) findViewById(R.id.input_one);
                EditText input_two = (EditText) findViewById(R.id.input_two);

                String index = input_one.getText().toString();
                String value = input_two.getText().toString();

                //ensure user has inputted two values.
                if(index.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input index to insert to."
                            , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else if(value.equals("")){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input value to insert."
                            , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {

                    //create bundle and insert values to be passed to new activity.
                    Bundle bundle = new Bundle();
                    bundle.putString("value",value);
                    bundle.putString("index",index);

                    //create new intent and pass it a bundle
                    Intent intent = new Intent(LinkedListInsert.this, LinkedListInsertSurfaceActivity.class);
                    intent.putExtras(bundle);

                    //start new activity
                    LinkedListInsert.this.startActivity(intent);

                }
            }
        });


    }
}
