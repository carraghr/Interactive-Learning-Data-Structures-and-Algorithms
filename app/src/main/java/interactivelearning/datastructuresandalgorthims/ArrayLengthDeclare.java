package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ArrayLengthDeclare extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arraydeclaration);
        Button declarationButton = (Button) findViewById(R.id.arraydeclarationbutton);
        Button initialisationButton = (Button) findViewById(R.id.arrayinitialisationbutton);

        declarationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "You sure showed that declaration button", Toast.LENGTH_SHORT);
                toast.show();
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
        textViewToChange.setText(R.string.array_declaration);

        textViewToChange = (TextView) findViewById(R.id.arraydeclaration);
        textViewToChange.setText(R.string.array_declaration_para);

        textViewToChange = (TextView) findViewById(R.id.arrayinitialisation);
        textViewToChange.setText(R.string.array_initialisation_para);
    }
}