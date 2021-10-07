package com.demo.ui;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoViewEx extends AppCompatActivity {

    private VideoView videoView;
    private int position = 0;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_videoview);
        videoView = (VideoView) findViewById(R.id.videoView);
        if (mediaController == null) {
            mediaController = new MediaController(this);
            videoView.setMediaController(mediaController);
        }

        try {
            Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.myvideo);
            videoView.setVideoURI(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }

        videoView.setOnPreparedListener(onPreparedListener);
    }

    OnPreparedListener onPreparedListener = new OnPreparedListener() {
        public void onPrepared(MediaPlayer mediaPlayer) {
            System.out.println("onPrepared");
            if (position == 0) {
                videoView.start();
            }
            mediaPlayer.setOnVideoSizeChangedListener(
                new MediaPlayer.OnVideoSizeChangedListener() {
                    @Override
                    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
                        System.out.println("onVideoSizeChanged");
                        mediaController.setAnchorView(videoView);
                    }
                });
        }
    };
}

