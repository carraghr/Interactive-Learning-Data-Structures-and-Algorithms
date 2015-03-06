package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;

public class TopicActivity extends Activity {

    private Topics topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        final TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.topics);

        try {

            //read topic file and get topics
            TopicFileHandler handler = new TopicFileHandler(TopicActivity.this);
            topics = handler.getTopics();
            handler.close();

            ListViewPopulate.populateTopicList(this,topics.getTopicNames());
            registerClickCallback();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registerClickCallback() {

        //register a click for each topic in list

        ListView list = (ListView) findViewById(R.id.ListViewTopicMenu);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view;
                String topic = textView.getText().toString();
                Bundle bundle;
                Intent intent;
                switch (topic) {
                    case "Arrays":

                        bundle = new Bundle();
                        bundle.putStringArray("subtopic",topics.getTopic("Arrays").getSubtopics());


                        intent = new Intent(TopicActivity.this,ArrayTopicActivity.class);
                        intent.putExtras(bundle);

                        TopicActivity.this.startActivity(intent);

                        break;

                    case "LinkedLists":

                        bundle = new Bundle();
                        bundle.putStringArray("subtopic",topics.getTopic("LinkedLists").getSubtopics());

                        intent = new Intent(TopicActivity.this,LinkedListTopicActivity.class);
                        intent.putExtras(bundle);

                        TopicActivity.this.startActivity(intent);

                        break;
                }
            }
        });
    }
}