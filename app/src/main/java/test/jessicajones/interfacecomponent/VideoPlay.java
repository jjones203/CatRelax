package test.jessicajones.interfacecomponent;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;
import android.widget.MediaController;

/**
 * VideoPlay creates a VideoView with the video file corresponding to the
 * video tile passed in the intent.The video displays on full screen and
 * loops indefinitely.
 */
public class VideoPlay extends Activity implements MediaPlayer.OnPreparedListener {

    /* Set up and start video */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // set flags for fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_video_play);

        // get the video file, use it to set VideoView
        Intent intent = getIntent();
        VideoView playVideoView = (VideoView)findViewById(R.id.playVideoView);
        String videoName;
        videoName = intent.getStringExtra(VideoMenu.TITLE).toLowerCase();
        int id = getResources().getIdentifier(videoName, "raw", getPackageName());
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + id);
        playVideoView.setVideoURI(uri);

        // finish setting up VideoView, start playing
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(playVideoView);
        playVideoView.setMediaController(mediaController);
        playVideoView.setOnPreparedListener(this);
        playVideoView.requestFocus();
        playVideoView.start();
    }

    //implement looping
    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.setLooping(true);
    }
}
