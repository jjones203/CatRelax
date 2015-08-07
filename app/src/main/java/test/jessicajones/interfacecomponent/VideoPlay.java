package test.jessicajones.interfacecomponent;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;
import android.widget.MediaController;

/**
 * VideoPlay creates a VideoView with the video file corresponding to the
 * video tile passed in the intent.The video displays on full screen and
 * loops indefinitely.
 */
public class VideoPlay extends Activity implements MediaPlayer.OnPreparedListener {
    String videoName;
    public static final String MY_PREFS_NAME = "MyPrefsFile";

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

    /**
     * When user touches screen, increments PawPoints for that title
     * (stored in SharedPreferences).
     * @param event
     * @return
     */
    public boolean onTouchEvent(MotionEvent event)
    {
        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String videoTitle = getIntent().getStringExtra(VideoMenu.TITLE);
        int count = prefs.getInt(videoTitle, 0); //0 is the default value.
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(videoTitle, ++count);
        editor.commit();

        return true;
    }
}
