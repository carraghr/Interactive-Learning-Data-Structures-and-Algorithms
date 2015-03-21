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
public class LinkedListRotation extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotation);
        setUPTutorial();
    }

    private void setUPTutorial(){
        //this method sets up the page to include information about the tutorial.

        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_rotation));

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(this.getResources().getString(R.string.linkedList_rotation_paragraph));

        Button previous = (Button) findViewById(R.id.previous);
        Button submit = (Button) findViewById(R.id.submit_one);

        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(LinkedListRotation.this, LinkedListRemoval.class);
                LinkedListRotation.this.startActivity(intent);
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText input_one = (EditText) findViewById(R.id.input_box_one);
                String rotations = input_one.getText().toString();

                if (rotations.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input a number of rotations you want to see."
                            , Toast.LENGTH_SHORT);
                    toast.show();
                }

                else {
                    Bundle bundle = new Bundle();
                    bundle.putString("numberOfRotates", rotations);
                    Intent intent = new Intent(LinkedListRotation.this, LinkedListRotationSurfaceActivity.class);
                    intent.putExtras(bundle);
                    LinkedListRotation.this.startActivity(intent);

                }
            }
        });

    }
}
