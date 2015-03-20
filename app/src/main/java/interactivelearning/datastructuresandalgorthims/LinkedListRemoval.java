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
public class LinkedListRemoval extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.one_input_page);
        setUI();
    }

    private void setUI(){
        //this method sets up the page to include information about the tutorial.

        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_removal));

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_removal_paragraph));

        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);
        Button submit = (Button) findViewById(R.id.submit_one);

        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListRemoval.this, LinkedListInsert.class);
                LinkedListRemoval.this.startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListRemoval.this, LinkedListRotation.class);
                LinkedListRemoval.this.startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText input_one = (EditText) findViewById(R.id.input_one);
                String index = input_one.getText().toString();

                if (index.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input index to remove"
                            , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Bundle bundle = new Bundle();
                    bundle.putString("index", index);
                    Intent intent = new Intent(LinkedListRemoval.this, LinkedListRemovalSurfaceActivity.class);
                    intent.putExtras(bundle);
                    LinkedListRemoval.this.startActivity(intent);

                }
            }
        });
    }
}
