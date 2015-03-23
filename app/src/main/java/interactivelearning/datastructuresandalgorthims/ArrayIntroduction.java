package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created on 01/03/2015.
 */

public class ArrayIntroduction extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI(){
        //this method sets up the page to include information about the tutorial.
        setContentView(R.layout.introduction);

        TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(this.getResources().getString(R.string.array_introduction));

        textViewToChange = (TextView) findViewById(R.id.first_text_block);
        textViewToChange.setText(this.getResources().getString(R.string.array_introduction_paragraph));

        Button nextButton = (Button) findViewById(R.id.next);

        //set up next topic button
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(ArrayIntroduction.this, ArrayDeclaration.class);
                ArrayIntroduction.this.startActivity(intent);
            }
        });
    }
}