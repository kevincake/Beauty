package cn.ifreedomer.beauty.activity.sign;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.flipboard.bottomsheet.commons.MenuSheetView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.callback.SimpleOnActivityForResultCallback;
import cn.ifreedomer.beauty.constants.ActivityResultConstants;
import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.entity.LogInResult;
import cn.ifreedomer.beauty.manager.AppManager;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.oss.AlipayOSSClient;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.ActivityForResultUtils;
import cn.ifreedomer.beauty.util.FileUtils;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.util.StringUtils;
import cn.ifreedomer.beauty.util.ToastUtil;

public class SignUpFullInfoActivity extends BaseActivity {

    @Bind(R.id.profile_image)
    SimpleDraweeView profileImage;
    @Bind(R.id.fullinfo_username_et)
    EditText fullinfoUsernameEt;
    @Bind(R.id.fullinfo_pwd_et)
    EditText fullinfoPwdEt;
    @Bind(R.id.fullinfo_sex_tv)
    TextView fullinfoSexTv;
    @Bind(R.id.fullinfo_commit_btn)
    Button fullinfoCommitBtn;
    @Bind(R.id.fullinfo_back2register_tv)
    TextView fullinfoBack2registerTv;
    @Bind(R.id.bottomsheet)
    BottomSheetLayout bottomSheetLayout;
    private String avatarName;
    private String phone;
    private SubscriberOnNextListener signUpSubscriber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_full_info);
        ButterKnife.bind(this);
        phone = getIntent().getStringExtra(IntentConstants.PHONE);
        signUpSubscriber = new SubscriberOnNextListener<LogInResult>() {

            @Override
            public void onNext(LogInResult logInResult) {
                AppManager.getInstance().saveUser(logInResult.getUser());
                AppManager.getInstance().setToken(logInResult.getToken());
                AppManager.getInstance().setLogin(true);
                IntentUtils.startMainActivity(SignUpFullInfoActivity.this);
                SignUpFullInfoActivity.this.finish();
            }
        };
    }

    @OnClick({R.id.profile_image, R.id.fullinfo_commit_btn, R.id.fullinfo_back2register_tv,R.id.fullinfo_sex_tv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_image:
                MenuSheetView menuSheetView =
                        new MenuSheetView(SignUpFullInfoActivity.this, MenuSheetView.MenuType.LIST, getString(R.string.select), new MenuSheetView.OnMenuItemClickListener() {
                            @Override
                            public boolean onMenuItemClick(MenuItem item) {
                                Toast.makeText(SignUpFullInfoActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                                if (bottomSheetLayout.isSheetShowing()) {
                                    bottomSheetLayout.dismissSheet();
                                }
                                if (item.getTitle().equals(getString(R.string.camera))){
                                    goCamera();
                                }else{
                                   goAlbum();
                                }

                                return true;
                            }
                        });
                menuSheetView.inflateMenu(R.menu.camera_photo);
                bottomSheetLayout.showWithSheetView(menuSheetView);
                break;
            case R.id.fullinfo_commit_btn:
                if (!StringUtils.isPwdValid(fullinfoPwdEt.getText().toString())){
                    ToastUtil.showTextToast(this,getString(R.string.pwd_notvalid));
                    return;
                }
                if (profileImage.getController()==null||TextUtils.isEmpty(avatarName)){
                    ToastUtil.showTextToast(this,getString(R.string.avatar_not_null));
                    return;
                };
                if(TextUtils.isEmpty(fullinfoUsernameEt.getText().toString())){
                    ToastUtil.showTextToast(this,getString(R.string.username_not_null));
                    return;
                }
                int sex = fullinfoSexTv.getText().toString().equals(getString(R.string.man))? HttpConstants.MAN:HttpConstants.WOMEN;
                HttpMethods.getInstance().postSignUp(new ProgressSubscriber<LogInResult>(signUpSubscriber, this),fullinfoUsernameEt.getText().toString(),avatarName,sex,phone,fullinfoPwdEt.getText().toString());
                break;
            case R.id.fullinfo_back2register_tv:
                break;
            case R.id.fullinfo_sex_tv:
                menuSheetView =   new MenuSheetView(SignUpFullInfoActivity.this, MenuSheetView.MenuType.LIST, getString(R.string.select), new MenuSheetView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(SignUpFullInfoActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (bottomSheetLayout.isSheetShowing()) {
                            bottomSheetLayout.dismissSheet();
                        }
                        if (item.getTitle().equals(getString(R.string.man))){
                            fullinfoSexTv.setText(item.getTitle());
                        }else{
                            fullinfoSexTv.setText(item.getTitle());
                        }

                        return true;
                    }
                });
                menuSheetView.inflateMenu(R.menu.sex);
                bottomSheetLayout.showWithSheetView(menuSheetView);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityForResultUtils.onActivityResult(requestCode,resultCode,data);
    }

    private void goAlbum() {
        final String photoName = FileUtils.getGeneratePhotoName();
        final String localFullPath = FileUtils.getPhotoFullPath(photoName);
        ActivityForResultUtils.startActivityForResult(this, ActivityResultConstants.ALBUM_CODE, IntentUtils.getSystemDefaultAlbumCropIntent(localFullPath), new SimpleOnActivityForResultCallback() {
            @Override
            public void success(Integer resultCode, Intent data) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    FileUtils.inputStream2File(inputStream,new File(localFullPath));
                    cropImg(photoName,localFullPath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void goCamera() {
        final String photoName = FileUtils.getGeneratePhotoName();
        final String localFullPath = FileUtils.getPhotoFullPath(photoName);
        ActivityForResultUtils.startActivityForResult(this, ActivityResultConstants.PHOTO_CODE, IntentUtils.getSystemDefaultCameraIntent(localFullPath), new SimpleOnActivityForResultCallback() {
            @Override
            public void success(Integer resultCode, Intent data) {
                cropImg(photoName,localFullPath);
            }
        });
    }


    private void cropImg(final String photoName, final String localPath) {
        Intent systemDefaultCropIntent = IntentUtils.getSystemDefaultCropIntent(localPath);
        ActivityForResultUtils.startActivityForResult(this, ActivityResultConstants.CROP_CODE, systemDefaultCropIntent, new SimpleOnActivityForResultCallback() {
            @Override
            public void success(Integer resultCode, Intent data) {

                AlipayOSSClient.getInstance().uploadAndSetImageView(photoName,localPath,profileImage);
                avatarName = photoName;
            }
        });
    }
}
