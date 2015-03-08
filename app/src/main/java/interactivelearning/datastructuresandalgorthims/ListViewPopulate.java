package interactivelearning.datastructuresandalgorthims;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Richard on 02/03/2015. - full implementation of populateTopicList
 */
public class ListViewPopulate {

    public static void populateTopicList(Activity context, String [] topics){
        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,                    //Context for the activity
                R.layout.topic_item,     //Layout to use (create)
                topics                  //Items to be displayed
        );
        //Configure the list view
        ListView list = (ListView) context.findViewById(R.id.ListViewTopicMenu);//this id is from activity_main
        list.setAdapter(adapter);
    }
}
