package cn.ifreedomer.beauty.activity.sign;

import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;

public class SignInActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_sign_in);
    }
}
