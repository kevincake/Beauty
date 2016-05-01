package cn.ifreedomer.beauty.activity.sign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.entity.IsPhoneRegister;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.ActivityForResultUtils;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.util.StringUtils;
import cn.ifreedomer.beauty.util.ToastUtil;
import cn.ifreedomer.beauty.widget.TimeView;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

public class SignUpActivity extends BaseActivity {

    @Bind(R.id.profile_image)
    SimpleDraweeView profileImage;
    @Bind(R.id.phonenum_et)
    EditText phoneNumEt;
    @Bind(R.id.verifycode_et)
    EditText verifycodeEt;
    @Bind(R.id.getcode_tv)
    TimeView getcodeTv;
    @Bind(R.id.register_btn)
    Button registerBtn;
    @Bind(R.id.go_login_tv)
    TextView goLoginTv;

    private SubscriberOnNextListener<IsPhoneRegister> verifyCodeSubscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        verifyCodeSubscribe = new SubscriberOnNextListener<IsPhoneRegister>() {
            @Override
            public void onNext(IsPhoneRegister isPhoneRegister) {
                if (isPhoneRegister==null){
                    return;
                }
                SMSSDK.getVerificationCode(getString(R.string.contry_code), phoneNumEt.getText().toString());
                getcodeTv.beginCountDown();
            }
        };
        InitSMSHandler();

    }

    public void InitSMSHandler() {
        EventHandler eh = new EventHandler() {

            @Override
            public void afterEvent(int event, int result, Object data) {

                if (result == SMSSDK.RESULT_COMPLETE) {
                    //回调完成
                    if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                        IntentUtils.startSignUpFullInfoActivity(phoneNumEt.getText().toString(),SignUpActivity.this);
                        //提交验证码成功
                    } else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                        //获取验证码成功
                    } else if (event == SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES) {
                        //返回支持发送验证码的国家列表
                    }
                } else {
                    ((Throwable) data).printStackTrace();
                }
            }
        };
        SMSSDK.registerEventHandler(eh);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityForResultUtils.onActivityResult(requestCode, resultCode, data);
//        ActivityForResultUtils.startActivityForResult(this, ActivityResultConstants.PHOTO_CODE,);
    }

    @OnClick({R.id.getcode_tv, R.id.register_btn, R.id.go_login_tv, R.id.profile_image})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.getcode_tv:
                if (!StringUtils.isPhone(phoneNumEt.getText().toString())) {
                    ToastUtil.showTextToast(this, getString(R.string.phone_format_error));
                    return;
                }
                //检查手机号是否已经注册
                HttpMethods.getInstance().getIsPhoneRegister(new ProgressSubscriber<IsPhoneRegister>(verifyCodeSubscribe, this), getString(R.string.contry_code));
                break;
            case R.id.register_btn:
//                IntentUtils.startSignUpFullInfoActivity(phoneNumEt.getText().toString(),this);

                if (!StringUtils.isPhone(phoneNumEt.getText().toString())) {
                    ToastUtil.showTextToast(this, getString(R.string.phone_format_error));
                    return;
                }
                if (TextUtils.isEmpty(verifycodeEt.getText().toString())) {
                    ToastUtil.showTextToast(this, getString(R.string.verifycode_null));
                    return;
                }
                SMSSDK.submitVerificationCode(getString(R.string.contry_code), phoneNumEt.getText().toString(), verifycodeEt.getText().toString());
                break;
            case R.id.go_login_tv:
                break;
            case R.id.profile_image:

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        SMSSDK.unregisterAllEventHandler();
    }
}
