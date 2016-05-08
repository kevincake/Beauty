package cn.ifreedomer.beauty.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.constants.Constants;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {
    private static final int NET_VIDEO_TYPE = 1;
    private static final int LOCAL_VIDEO_TYPE = 2;
    private String path;
    private VideoView mVideoView;
    private EditText mEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video_player);

        mVideoView = (VideoView) findViewById(R.id.video_view);
        Intent urlIntent = getIntent();
        String url = urlIntent.getStringExtra(Constants.VIDEO_URL);
        int type = getUrlType(url);
        switch (type) {
            case LOCAL_VIDEO_TYPE:
                openVideoFromLocal(url);
                break;
            case NET_VIDEO_TYPE:
                openVideoFromUrl(url);
                break;

        }

}

    public int getUrlType(String url) {
        if (url == null) {
            return -1;
        }
        if (url.contains("http://")) {
            return NET_VIDEO_TYPE;
        } else {
            return LOCAL_VIDEO_TYPE;
        }
    }

    public void startPlay(View view) {
        String url = mEditText.getText().toString();
        path = url;
        if (!TextUtils.isEmpty(url)) {
            mVideoView.setVideoPath(url);
        }
    }

    private void inputStream2File(InputStream ins, File file) {
//        File file = new File(path);
        OutputStream outputStream = null;
        try {

            outputStream = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] bytes = new byte[1024];
            int read = 0;
            while ((read = ins.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.close();
//            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void openVideoFromUrl(String url) {
        mVideoView.setVideoURI(Uri.parse(url));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }

    public void openVideoFromLocal(String url) {
        mVideoView.setVideoURI(Uri.parse(url));
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.requestFocus();

        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                // optional need Vitamio 4.0
                mediaPlayer.setPlaybackSpeed(1.0f);
            }
        });
    }

    public void openVideo(View View) {
        mVideoView.setVideoPath(path);
    }

}
