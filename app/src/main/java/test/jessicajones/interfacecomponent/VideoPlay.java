package test.jessicajones.interfacecomponent;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

public class VideoPlay extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_play);

        Intent intent = getIntent();
        String title = intent.getStringExtra(VideoMenu.TITLE);
        TextView titleText = (TextView) findViewById(R.id.playVideoTitle);
        titleText.setText(title);

        VideoView playVideoView = (VideoView)findViewById(R.id.playVideoView);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.sample_mpeg4);
        playVideoView.setVideoURI(uri);

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(playVideoView);
        playVideoView.setMediaController(mediaController);

        playVideoView.requestFocus();
        playVideoView.start();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_video_play, menu);
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
