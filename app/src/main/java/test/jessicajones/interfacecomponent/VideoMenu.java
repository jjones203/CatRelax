package test.jessicajones.interfacecomponent;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.ListActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class VideoMenu extends ListActivity {

    public final static String TITLE = "test.jessicajones.interfacecomponent.TITLE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_video_menu);


        String[] videoArray = getResources().getStringArray(R.array.video_array);

        // Define a new adapter
        ArrayAdapter<String> mAdapter;
        mAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, videoArray);

        setListAdapter(mAdapter);



        // Define the listener interface
        AdapterView.OnItemClickListener mListener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                Intent intent = new Intent(getApplicationContext(),VideoPlay.class);
                String title = (String) (((TextView) view).getText());
                intent.putExtra(TITLE,title);
                startActivity(intent);
            }
        };

        // Get the ListView and wired the listener
        ListView listView = getListView();
        listView.setOnItemClickListener(mListener);
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

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
