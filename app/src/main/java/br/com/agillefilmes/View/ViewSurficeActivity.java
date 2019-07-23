package br.com.agillefilmes.View;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.widget.MediaController;

import br.com.agillefilmes.R;

public class ViewSurficeActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener{
    private MediaPlayer mediaPlayer;
    private TextureView videoview;
    private MediaController mediaController;
    String videoPath = "https://firebasestorage.googleapis.com/v0/b/agillefilmes.appspot.com/o/percy%20jackson%202.mp4?alt=media&token=b2f36c46-4275-4dc2-9f72-ed77d8a35c17";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_view_surfice);

        videoview=findViewById(R.id.video_view);
        videoview.setSurfaceTextureListener(this);
        if (savedInstanceState != null

                && savedInstanceState.getInt("currentposition") != 0) {



            mediaPlayer.seekTo(savedInstanceState.getInt("currentposition"));
            mediaPlayer.start();

        }
    }


    @Override
    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        Surface surface_class = new Surface(surface);
        mediaPlayer = new MediaPlayer();
        try{
            mediaController = new MediaController(this);
            mediaPlayer.setDataSource(ViewSurficeActivity.this, Uri.parse(videoPath));
            mediaPlayer.setSurface(surface_class);
            mediaPlayer.setLooping(false);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });

        }catch (Exception e){
            e.printStackTrace();

        }

    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if(mediaPlayer != null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        return false;
    }

    @Override
    public void onSurfaceTextureUpdated(SurfaceTexture surface) {

    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("currentposition", mediaPlayer.getCurrentPosition());
        super.onSaveInstanceState(outState);
    }

}
