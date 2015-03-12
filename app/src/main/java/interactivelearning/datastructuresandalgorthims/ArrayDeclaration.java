package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ArrayDeclaration extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI(){
        setContentView(R.layout.arraydeclaration);
        Button declarationButton = (Button) findViewById(R.id.submit_one);
        Button initialisationButton = (Button) findViewById(R.id.submit_five);

        declarationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText one_input = (EditText) findViewById(R.id.input_box_one);
                String test = one_input.getText().toString();
                Toast toast = Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT);
                if (test == null)
                    toast = Toast.makeText(getApplicationContext(),"String is null", Toast.LENGTH_SHORT);
                else
                    toast = Toast.makeText(getApplicationContext(),"String is empty", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(ArrayDeclaration.this,SurfaceActivity.class);
                ArrayDeclaration.this.startActivity(intent);
            }
        });

        initialisationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "x", Toast.LENGTH_SHORT);
                toast.show();

                Intent intent = new Intent(ArrayDeclaration.this,SurfaceActivity.class);
                ArrayDeclaration.this.startActivity(intent);
            }
        });

        //TODO write function to out new strings for declaring arrays of set length.
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_declaration);

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(R.string.array_declaration_paragraph);

        textViewToChange = (TextView) findViewById(R.id.second_text_block);
        textViewToChange.setText(R.string.array_initialisation_paragraph);
    }
}