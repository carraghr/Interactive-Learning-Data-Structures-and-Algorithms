package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class TopicActivity extends Activity {

    private Topics topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        String topic = this.getResources().getString(R.string.topics);
        final TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(topic);

        try {
            TopicFileHandler handler = new TopicFileHandler(TopicActivity.this);
            topics = new Topics(handler.getTopics());

            populateListView();
            registerClickCallback();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateListView() {

        //Get list of topics from topics
        String[] topicsNames = topics.getTopicNames();

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,                    //Context for the activity
                R.layout.topic_item,     //Layout to use (create)
                topicsNames                  //Items to be displayed
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
                String topic = textView.getText().toString();

                switch (topic) {
                    case "Arrays":

                        Bundle bundle = new Bundle();
                        bundle.putStringArray("subtopic",topics.getTopic("Arrays").getSubtopics());

                        Intent intent = new Intent(TopicActivity.this,ArrayTopicActivity.class);
                        intent.putExtras(bundle);

                        TopicActivity.this.startActivity(intent);

                        break;
                }
            }
        });
    }
}