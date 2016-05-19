package cn.ifreedomer.beauty.activity.sign;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.entity.jsonbean.LogInResult;
import cn.ifreedomer.beauty.manager.AppManager;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.util.LogUtil;
import cn.ifreedomer.beauty.util.StringUtils;
import cn.ifreedomer.beauty.util.ToastUtil;

public class SignInActivity extends BaseActivity implements View.OnClickListener {


    @Bind(R.id.profile_image)
    SimpleDraweeView profileImage;
    @Bind(R.id.phonenum_et)
    EditText phonenumEt;
    @Bind(R.id.pwd_et)
    EditText pwdEt;
    @Bind(R.id.login_btn)
    Button loginBtn;
    @Bind(R.id.login_forget_tv)
    TextView loginForgetTv;
    @Bind(R.id.goto_register_tv)
    TextView gotoRegisterTv;
    private SubscriberOnNextListener signInSubscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_sign_in);
        ButterKnife.bind(this);
        signInSubscriber = new SubscriberOnNextListener<LogInResult>() {

            @Override
            public void onNext(LogInResult logInResult) {
                AppManager.getInstance().saveUser(logInResult.getUser());
                AppManager.getInstance().setToken(logInResult.getToken());
                AppManager.getInstance().setLogin(true);
                IntentUtils.startMainActivity(SignInActivity.this);
                LogUtil.error("sign",logInResult.toString());
                SignInActivity.this.finish();


                ToastUtil.showTextToast(SignInActivity.this, getString(R.string.login_success));
            }
        };
    }


    @OnClick({R.id.login_btn, R.id.goto_register_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                if (!StringUtils.isPhone(phonenumEt.getText().toString())) {
                    ToastUtil.showTextToast(this, getString(R.string.phone_format_error));
                    return;
                }
                if (!StringUtils.isPwdValid(phonenumEt.getText().toString())) {
                    ToastUtil.showTextToast(this, getString(R.string.pwd_notvalid));
                    return;
                }
                HttpMethods.getInstance().getSignIn(new ProgressSubscriber<LogInResult>(signInSubscriber, this), phonenumEt.getText().toString(), pwdEt.getText().toString());
                break;
            case R.id.goto_register_tv:
                IntentUtils.startSignUpActivity(this);
                this.finish();
                break;
        }
    }
}
