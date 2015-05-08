package test.jessicajones.interfacecomponent;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
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
import android.widget.Toast;


/**
 * VideoMenu displays list of video titles user can select. If an item is selected,
 * VideoPlay is launched and the title is passed to VideoPlay as an intent.
 * @author jessica jones
 */
public class VideoMenu extends ListActivity {

    public final static String TITLE = "test.jessicajones.interfacecomponent.TITLE";

    /* Create menu; set up listener */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("CatRelax Video Menu");

        // Get video titles
        String[] videoArray = getResources().getStringArray(R.array.video_array);

        // Define a new adapter
        ArrayAdapter rowAdapter;
        rowAdapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1, videoArray);
        setListAdapter(rowAdapter);

        // Define the listener interface
        AdapterView.OnItemClickListener menuListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getApplicationContext(),VideoPlay.class);
                String title = (String) (((TextView) view).getText());
                intent.putExtra(TITLE,title);
                startActivity(intent);
            }
        };

        // Get the ListView and wire the listener
        ListView listView = getListView();
        listView.setOnItemClickListener(menuListener);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // changing default in attempt to solve Galaxy-related error
        if (id == R.id.action_settings) {
            Intent settingsIntent = new Intent(getApplicationContext(), VideoMenu.class);
            startActivity(settingsIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
