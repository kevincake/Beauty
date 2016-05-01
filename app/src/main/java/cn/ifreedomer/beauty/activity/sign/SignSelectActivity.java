package cn.ifreedomer.beauty.activity.sign;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.VideoView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.adapter.SignSelectPageApdater;
import cn.ifreedomer.beauty.listener.SignSelectPageListener;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.widget.AutoSwitchViewPager;

public class SignSelectActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.bg_videoview)
    VideoView bgVideoview;
    @Bind(R.id.ad_page)
    AutoSwitchViewPager adPageView;
    @Bind(R.id.dot1)
    ImageView dot1;
    @Bind(R.id.dot2)
    ImageView dot2;
    @Bind(R.id.dot3)
    ImageView dot3;
    @Bind(R.id.dot4)
    ImageView dot4;
    @Bind(R.id.dot_layout)
    LinearLayout dotLayout;
    @Bind(R.id.register_btn)
    Button registerBtn;
    @Bind(R.id.login_btn)
    Button loginBtn;
    public static final int MAX_PAGE = 4;
    private List<ImageView> dots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_select);
        ButterKnife.bind(this);
        addAllDots2List();
        initViewPager();
        initVideoView();
    }
    void addAllDots2List() {
        dots = new ArrayList<>();
        dots.add(dot1);
        dots.add(dot2);
        dots.add(dot3);
        dots.add(dot4);
    }
    private void initViewPager() {
        ArrayList<View> views = new ArrayList<View>();
        for (int i = 0; i < MAX_PAGE; i++) {
            views.add(View.inflate(this, R.layout.sign_select_pageitem, null));
            TextView tv = (TextView) views.get(i).findViewById(R.id.power_text);
            tv.setText(getString(R.string.main_des1 + i));
        }
        adPageView.setAdapter(new SignSelectPageApdater(views));
        adPageView.addOnPageChangeListener(new SignSelectPageListener(dots));
        adPageView.beginAutoSwitch();
    }


    void initVideoView() {

        final String videopath = "android.resource://" + this.getPackageName() + "/" + R.raw.intro;
        bgVideoview.setVideoURI(Uri.parse(videopath));
        bgVideoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                bgVideoview.setVideoPath(videopath);
                bgVideoview.start();

            }
        });
        bgVideoview.start();
        setVideoViewFullScreen();
    }
    void  setVideoViewFullScreen(){
        DisplayMetrics metrics = new DisplayMetrics(); getWindowManager().getDefaultDisplay().getMetrics(metrics);
        android.widget.RelativeLayout.LayoutParams params = (android.widget.RelativeLayout.LayoutParams) bgVideoview.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        params.leftMargin = 0;
        bgVideoview.setLayoutParams(params);
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        bgVideoview.pause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bgVideoview.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    @OnClick({R.id.register_btn,R.id.login_btn})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register_btn:
                IntentUtils.startSignUpActivity(this);
                break;
            case R.id.login_btn:
                IntentUtils.startSignInActivity(this);
                break;
            case R.id.login_forget_tv:
                break;
        }
    }
}
