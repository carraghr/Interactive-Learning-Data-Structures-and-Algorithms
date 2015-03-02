package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Richard on 02/03/2015.
 */
public class ListViewPop {

    public static void populateListView(Activity context,String [] list) {

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,                        //Context for the activity
                R.layout.topic_item,            //Layout to use (create)
                list                            //Items to be displayed
        );

        //Configure the list view
        ListView listView = (ListView) context.findViewById(R.id.ListViewTopicMenu);//this id is from activity_main
        listView.setAdapter(adapter);
    }
}
