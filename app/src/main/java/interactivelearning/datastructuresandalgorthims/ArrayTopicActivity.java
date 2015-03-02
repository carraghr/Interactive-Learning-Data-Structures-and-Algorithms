package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by Richard on 27/02/2015.
 * This activity
 */
public class ArrayTopicActivity extends Activity {

    String [] topics;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        String topic = this.getResources().getString(R.string.arrays);
        final TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(topic);

        Bundle bundle = this.getIntent().getExtras();
        topics = bundle.getStringArray("subtopic");

        //topics = new String[]{"a","b","c"}; //= intent.getStringArrayExtra("subtopic");

        populateListView(topics);
        registerClickCallback();
    }

    private void populateListView(String[] topics) {

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,                    //Context for the activity
                R.layout.topic_item,     //Layout to use (create)
                topics                  //Items to be displayed
        );
        //Configure the list view
        ListView list = (ListView) findViewById(R.id.ListViewTopicMenu);//this id is from activity_main
        list.setAdapter(adapter);

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

                    case "Declare Array Length":

                        intent = new Intent(ArrayTopicActivity.this,ArrayLengthDeclare.class);
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