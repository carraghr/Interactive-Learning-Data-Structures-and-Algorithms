package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Jessica on 13/03/2015.
 */
public class CodeTopicActivity extends Activity{

    String [] subtopics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        setContentView(R.layout.activity_topic);

        final TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.Code_topic_activity);

        Bundle bundle = this.getIntent().getExtras();
        subtopics = bundle.getStringArray("subtopic");

        ListViewPopulate.populateTopicList(this, subtopics);

        registerClickCallback();
    }

    private void registerClickCallback() {
        //register a click for each topic in list
        ListView list = (ListView) findViewById(R.id.ListViewTopicMenu);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;

                Intent intent;

                switch(textView.getText().toString()){
                    case "Array Merge Sort":

                        intent = new Intent(CodeTopicActivity.this,LinkedListIntro.class);
                        CodeTopicActivity.this.startActivity(intent);

                        break;

                    case "Array Bubble Sort":

                        intent = new Intent(CodeTopicActivity.this,LinkedListDeclaration.class);
                        CodeTopicActivity.this.startActivity(intent);

                        break;

                    case "Array Select Sort":

                        intent = new Intent(CodeTopicActivity.this,LinkedListInsert.class);
                        CodeTopicActivity.this.startActivity(intent);

                        break;

                    case "Array Linear Search":

                        intent = new Intent(CodeTopicActivity.this,LinkedListRemoval.class);
                        CodeTopicActivity.this.startActivity(intent);

                        break;

                    case "Array Binary Search":

                        intent = new Intent(CodeTopicActivity.this,LinkedListRotation.class);
                        CodeTopicActivity.this.startActivity(intent);

                        break;
                }
            }
        });
    }
}
