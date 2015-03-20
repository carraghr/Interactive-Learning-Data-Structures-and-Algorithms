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
        final Button declarationButton = (Button) findViewById(R.id.submit_one);
        final Button initialisationButton = (Button) findViewById(R.id.submit_five);

        Button previous = (Button) findViewById(R.id.previous);
        Button next = (Button) findViewById(R.id.next);

        previous.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayDeclaration.this, ArrayIntroduction.class);
                ArrayDeclaration.this.startActivity(intent);
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(ArrayDeclaration.this, ArraySelectSort.class);
                ArrayDeclaration.this.startActivity(intent);
            }
        });

        declarationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                EditText one_input = (EditText) findViewById(R.id.input_box_one);
                String input = one_input.getText().toString();

                if(input.equals("")) {
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input a length for an array", Toast.LENGTH_SHORT);
                    toast.show();
                }else {

                    Bundle bundle = new Bundle();
                    bundle.putString("type","Declaration");
                    bundle.putString("number_of_Slots", input);
                    Intent intent = new Intent(ArrayDeclaration.this, ArrayDeclarationSurfaceActivity.class);
                    intent.putExtras(bundle);
                    ArrayDeclaration.this.startActivity(intent);
                }
            }
        });

        initialisationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                EditText one_input = (EditText) findViewById(R.id.input_one);
                EditText two_input = (EditText) findViewById(R.id.input_two);
                EditText three_input = (EditText) findViewById(R.id.input_three);
                EditText four_input = (EditText) findViewById(R.id.input_four);
                EditText five_input = (EditText) findViewById(R.id.input_five);

                String [] values = new String[]{one_input.getText().toString(),
                                                two_input.getText().toString(),
                                                three_input.getText().toString(),
                                                four_input.getText().toString(),
                                                five_input.getText().toString()};
                values = InputControls.sortedValues(values);

                if(values.length == 0){
                    Toast toast = Toast.makeText(getApplicationContext(), "Please input values to initialize array"
                                                                        , Toast.LENGTH_SHORT);
                    toast.show();
                }
                else {
                    Bundle bundle = new Bundle();

                    bundle.putString("type","Initialization");
                    bundle.putString("number_of_Slots", ""+values.length);
                    bundle.putStringArray("values",values);
                    Intent intent = new Intent(ArrayDeclaration.this, ArrayDeclarationSurfaceActivity.class);
                    intent.putExtras(bundle);
                    ArrayDeclaration.this.startActivity(intent);

                }
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