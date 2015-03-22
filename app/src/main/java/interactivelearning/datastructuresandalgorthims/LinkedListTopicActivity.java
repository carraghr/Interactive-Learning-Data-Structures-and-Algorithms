package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created on 06/03/2015.
 */
public class LinkedListTopicActivity extends Activity{

    String [] subtopics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        setContentView(R.layout.activity_topic);

        //set topic header
        final TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.linkedList_topic_activity);

        //get subtopics to be shown
        Bundle bundle = this.getIntent().getExtras();
        subtopics = bundle.getStringArray("subtopic");

        //pop list view
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
                case "Introduction":

                    intent = new Intent(LinkedListTopicActivity.this,LinkedListIntro.class);
                    LinkedListTopicActivity.this.startActivity(intent);

                    break;

                case "Declaration":

                    intent = new Intent(LinkedListTopicActivity.this,LinkedListDeclaration.class);
                    LinkedListTopicActivity.this.startActivity(intent);

                    break;

                case "Adding Nodes":

                    intent = new Intent(LinkedListTopicActivity.this,LinkedListInsert.class);
                    LinkedListTopicActivity.this.startActivity(intent);

                    break;

                case "Removing Nodes":

                    intent = new Intent(LinkedListTopicActivity.this,LinkedListRemoval.class);
                    LinkedListTopicActivity.this.startActivity(intent);

                    break;

                case "Rotation":

                    intent = new Intent(LinkedListTopicActivity.this,LinkedListRotation.class);
                    LinkedListTopicActivity.this.startActivity(intent);

                    break;
            }
        }
        });
    }
}

