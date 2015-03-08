package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Jessica on 06/03/2015. - Class name.
 * Edited by Richard on 7/3/15 - now extends Activity
 *                             -
 */
public class LinkedListDeclaration extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arraydeclaration);
        Button declarationButton = (Button) findViewById(R.id.arraydeclarationbutton);
        Button initialisationButton = (Button) findViewById(R.id.array_linear_search_button);

        declarationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "You sure showed that declaration button", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(LinkedListDeclaration.this,SurfaceActivity.class);
                LinkedListDeclaration.this.startActivity(intent);
            }
        });

        initialisationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "You sure showed that initialisation button", Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //TODO write function to out new strings for declaring arrays of set length.
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.linkedList_declaration);

        textViewToChange = (TextView) findViewById(R.id.array_text);
        textViewToChange.setText(R.string.linkedlist_declaration_paragraph);

        textViewToChange = (TextView) findViewById(R.id.arrayinitialisation);
        textViewToChange.setText(R.string.linkedList_initialisation_para);
    }
}