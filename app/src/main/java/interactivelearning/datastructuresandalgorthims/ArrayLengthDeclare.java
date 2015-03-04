package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Richard on 01/03/2015.
 */
public class ArrayLengthDeclare extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.arraydeclaration);
        setUPTutorial();
    }

    private void setUPTutorial() {
        //TODO write function to out new strings for declaring arrays of set length.
        String topic = this.getResources().getString(R.string.array_declaration);
        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(topic);

        String intro = this.getResources().getString(R.string.array_declare_para);
        textViewToChange = (TextView) findViewById(R.id.arraydeclaration);
        textViewToChange.setText(intro);
    }
}
