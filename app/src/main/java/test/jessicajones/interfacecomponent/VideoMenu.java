package test.jessicajones.interfacecomponent;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ListActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;


/**
 * VideoMenu displays list of video titles user can select. If an item is selected,
 * VideoPlay is launched and the title is passed to VideoPlay as an intent.
 * @author jessica jones
 */
public class VideoMenu extends ListActivity {

    public final static String TITLE = "test.jessicajones.interfacecomponent.TITLE";
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    /* Create menu; set up listener */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CatRelax Video Menu");
        setUpList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        // changing default in attempt to solve Galaxy-related error
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(getApplicationContext(), VideoMenu.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // Overriding onResume so number of PawPoints refreshed when user returns to menu
    @Override
    public void onResume()
    {
        super.onResume();
        setUpList();
    }

    /**
     * Helper method for creating list of menu items; called by onCreate and onResume
      */
    private void setUpList()
    {
        // Get video titles from strings.xml
        String[] videoArray = getResources().getStringArray(R.array.video_array);
        int numVideos = videoArray.length;
        String[] menuTextArray = new String[numVideos];
        for (int i = 0; i < numVideos; i++) {
            String videoName = videoArray[i];
            // Retrieve number PawPoints via SharedPreferences
            SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            int points = prefs.getInt(videoName, 0); //0 is the default value.
            menuTextArray[i] = videoName + " - " + points +" PawPoints";
        }

        // Define a new adapter
        ArrayAdapter rowAdapter;
        rowAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, menuTextArray);
        setListAdapter(rowAdapter);

        // Define the listener interface
        AdapterView.OnItemClickListener menuListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getApplicationContext(),VideoPlay.class);
                String title =  (((TextView) view).getText()).toString().split(" ")[0];
                // Pass title of video selected to VideoPlay with intent
                intent.putExtra(TITLE,title);
                startActivity(intent);
            }
        };

        // Get the ListView and wire the listener
        ListView listView = getListView();
        listView.setOnItemClickListener(menuListener);
    }
}
