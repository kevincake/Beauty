package cn.ifreedomer.beauty.activity;

import android.os.Bundle;
import android.os.Handler;

import com.umeng.analytics.MobclickAgent;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.util.IntentUtils;

public class SplashActivity extends BaseActivity {
    public static final int SHOW_TIME = 3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                IntentUtils.startSignSelectActivity(SplashActivity.this);
            }
        },SHOW_TIME);
    }
    public void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
    public void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }
}
