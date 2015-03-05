package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Richard on 01/03/2015.
 */
public class ArrayLengthDeclare extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arraydeclaration);
        Button button = (Button) findViewById(R.id.arraydeclarationbutton);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast toast = Toast.makeText(getApplicationContext(), "You sure showed me!", Toast.LENGTH_SHORT);
                toast.show();
            }
        });
        //TODO write function to out new strings for declaring arrays of set length.
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_declaration);

        textViewToChange = (TextView) findViewById(R.id.arraydeclaration);
        textViewToChange.setText(R.string.array_declare_para);
    }
}
