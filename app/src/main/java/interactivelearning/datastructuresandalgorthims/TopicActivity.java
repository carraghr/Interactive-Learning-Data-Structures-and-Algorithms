package interactivelearning.datastructuresandalgorthims;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;


public class TopicActivity extends ActionBarActivity {

    private Topic [] topics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        Toast.makeText(TopicActivity.this, "before try", Toast.LENGTH_LONG).show();

        try {

            TopicFileHandler handler = new TopicFileHandler(TopicActivity.this);
            Toast.makeText(TopicActivity.this, "In try" + handler.numberOfTopics, Toast.LENGTH_LONG).show();
            topics = handler.getTopics();
            Toast.makeText(TopicActivity.this, "In try2", Toast.LENGTH_LONG).show();

            populateListView(getTopicNames());
            registerClickCallback();

        } catch (IOException e) {
            Toast.makeText(TopicActivity.this, "In catch", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        //populateListView();
        //registerClickCallback();
    }

    private String [] getTopicNames(){
        String [] names = new String [topics.length];
        for(int i=0;i<topics.length;i++){
            names[i] = topics[i].getTopicName();
        }
        return names;
    }
    private void populateListView(String [] topics) {

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
                String message ="You clicked # " + position
                        + ", which is string:"+ textView.getText().toString();
                Toast.makeText(TopicActivity.this, message, Toast.LENGTH_LONG).show();



                // Start OpenGL activity

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_topic, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
