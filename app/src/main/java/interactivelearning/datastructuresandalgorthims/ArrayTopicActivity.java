package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created on 27/02/2015.
 */
public class ArrayTopicActivity extends Activity {

    String [] subtopics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUI();
    }

    private void setUI() {
        setContentView(R.layout.activity_topic);

        final TextView textViewToChange = (TextView) findViewById(R.id.topic);
        textViewToChange.setText(R.string.array_topic_activity);

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

                        intent = new Intent(ArrayTopicActivity.this,ArrayIntroduction.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;

                    case "Declare an Array":

                        intent = new Intent(ArrayTopicActivity.this,ArrayDeclaration.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;

                    case "Select Sort":

                        intent = new Intent(ArrayTopicActivity.this,ArraySelectSort.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;

                    case "Bubble Sort":

                        intent = new Intent(ArrayTopicActivity.this,ArrayBubbleSort.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;

                    case "Merge Sort":

                        intent = new Intent(ArrayTopicActivity.this,ArrayMergeSort.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;

                    case "Linear Search":

                        intent = new Intent(ArrayTopicActivity.this,ArrayLinearSearch.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;

                    case "Binary Search":

                        intent = new Intent(ArrayTopicActivity.this,ArrayBinarySearch.class);
                        ArrayTopicActivity.this.startActivity(intent);

                        break;
                }
            }
        });
    }
}