package br.com.agillefilmes;


import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;


public class ViewActivity extends AppCompatActivity  {

    VideoView videoView;
    String videoPath = "https://firebasestorage.googleapis.com/v0/b/agillefilmes.appspot.com/o/percy%20jackson%202.mp4?alt=media&token=b2f36c46-4275-4dc2-9f72-ed77d8a35c17";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        videoView = findViewById(R.id.video_view);

        Uri uri = Uri.parse(videoPath);
        videoView.setVideoURI(uri);

        if (savedInstanceState != null

                && savedInstanceState.getInt("currentposition") != 0) {



            videoView.seekTo(savedInstanceState.getInt("currentposition"));
            videoView.requestFocus(90);
            videoView.start();


        }

        MediaController mediaController = new MediaController(this);
        videoView.setMediaController(mediaController);
        mediaController.setAnchorView(videoView);

        videoView.requestFocus();

        videoView.start();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentposition", videoView.getCurrentPosition());
        super.onSaveInstanceState(outState);
    }
    @Override
    protected void onPause() {

        super.onPause();
    }
    public void VoltarTela (View view){
        Intent intent = new Intent(ViewActivity.this, MainActivity.class);
        startActivity(intent);
        videoView.stopPlayback();

    }

}