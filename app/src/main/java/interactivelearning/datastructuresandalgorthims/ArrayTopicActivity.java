package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Richard on 27/02/2015.
 * This activity
 */
public class ArrayTopicActivity extends Activity {

    String [] subtopics;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        final TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.arrays_topic_heading);

        Bundle bundle = this.getIntent().getExtras();
        subtopics = bundle.getStringArray("subtopic");


       // populateListView(topics);

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

                        intent = new Intent(ArrayTopicActivity.this,ArrayIntro.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;

                    case "Declare an Array":

                        intent = new Intent(ArrayTopicActivity.this,ArrayDeclare.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;
/*
                    case "Declare Array Filled with Values":

                        intent = new Intent(ArrayTopicActivity.this,ArrayValuesDeclare.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;

                    case "Sorting":

                        intent = new Intent(ArrayTopicActivity.this,ArraySearch.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;

                    case "Searching":

                        intent = new Intent(ArrayTopicActivity.this,ArraySort.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;*/
                }
            }
        });
    }
}