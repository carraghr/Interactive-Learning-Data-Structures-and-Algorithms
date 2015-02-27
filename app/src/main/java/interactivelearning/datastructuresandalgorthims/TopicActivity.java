package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class TopicActivity extends Activity {

    private Topics topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        try {

            TopicFileHandler handler = new TopicFileHandler(TopicActivity.this);
            topics = new Topics(handler.getTopics());

            populateListView(topics.getTopicNames());
            registerClickCallback();

        } catch (IOException e) {
            e.printStackTrace();
        }
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
                String message = "You clicked # " + position
                        + ", which is string: " + textView.getText().toString();
                Toast.makeText(TopicActivity.this, message, Toast.LENGTH_LONG).show();

                // Start OpenGL activity

            }
        });
    }
}