package cn.ifreedomer.beauty.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.constants.Constants;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.util.WindowUtil;
import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class VideoPlayerActivity extends BaseActivity implements MediaPlayer.OnCompletionListener {
    private static final int NET_VIDEO_TYPE = 1;
    private static final int LOCAL_VIDEO_TYPE = 2;
    @Bind(R.id.video_view)
    VideoView mVideoView;
    private String path;
    private boolean isShowing = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        setContentView(R.layout.activity_video_player);
        ButterKnife.bind(this);

//        mVideoView = (VideoView) findViewById(R.id.video_view);
        mVideoView.setOnCompletionListener(this);
        ViewGroup.LayoutParams layoutParams = mVideoView.getLayoutParams();
        layoutParams.height = (int) getResources().getDimension(R.dimen.dimen_dp250);

//        mediacontroller.setMediaPlayer(mVideoView);
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


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("progress", mVideoView.getCurrentPosition());
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mVideoView.seekTo(savedInstanceState.getLong("progress"));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ViewGroup.LayoutParams layoutParams = mVideoView.getLayoutParams();
        isShowing = mVideoView.getMediaController().isShowing();
//        mVideoView.getMediaController().setVisibility(View.GONE);
        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            layoutParams.height = WindowUtil.getWindowSize(this).y;
            layoutParams.width = WindowUtil.getWindowSize(this).x;
//            setContentView(R.layout.activity_video_player);
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            layoutParams.height = (int) getResources().getDimension(R.dimen.dimen_dp250);
            layoutParams.width = WindowUtil.getWindowSize(this).x;

//            setContentView(R.layout.activity_video_player);
        }
        mVideoView.setTag(layoutParams);
        mVideoView.setMediaController(new MediaController(this));
        if (isShowing) {
            mVideoView.getMediaController().show();
        }
//        int[] location = new int[2];
//        mVideoView.getLocationOnScreen(location);
//        LogUtil.error("size","height="+location[1]+"===width="+location[1]);
//        mVideoView.getMediaController().setIs
//        mVideoView.getMediaController().updatePos();

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
//        String url = mEditText.getText().toString();
//        path = url;
//        if (!TextUtils.isEmpty(url)) {
//            mVideoView.setVideoPath(url);
//        }
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

    @Override
    public void onCompletion(MediaPlayer mp) {
        IntentUtils.startGoodListActivity(this);
    }
}
