package cn.ifreedomer.beauty.activity.personcenter;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
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
import cn.ifreedomer.beauty.entity.User;
import cn.ifreedomer.beauty.manager.AppManager;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.oss.AlipayOSSClient;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.ActivityForResultUtils;
import cn.ifreedomer.beauty.util.FileUtils;
import cn.ifreedomer.beauty.util.ImageUtil;
import cn.ifreedomer.beauty.util.IntentUtils;

public class EditProfileActivity extends BaseActivity implements MenuItem.OnMenuItemClickListener {

    @Bind(R.id.profile_sv)
    SimpleDraweeView profileSv;
    @Bind(R.id.cover_sv)
    SimpleDraweeView coverSv;

    @Bind(R.id.username_et)
    EditText usernameEt;
    @Bind(R.id.usernameWrapper)
    TextInputLayout usernameWrapper;
    @Bind(R.id.password_et)
    EditText passwordEt;
    @Bind(R.id.password_rapper)
    TextInputLayout passwordRapper;
    @Bind(R.id.signture_et)
    EditText signtureEt;
    @Bind(R.id.signture_rapper)
    TextInputLayout signtureRapper;
    @Bind(R.id.bottomsheet)
    BottomSheetLayout bottomSheetLayout;
    private String avatarPath;
    private String coverPath;
    private int optionType = 1;
    private static final int PROFILE_TYPE = 1;
    private static final int COVER_TYPE = 2;
    private SubscriberOnNextListener<User> updateSub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);
        initView();
        initNetWork();
        initToolbar();
    }

    private void initNetWork() {
        updateSub = new SubscriberOnNextListener<User>() {
            @Override
            public void onNext(User user) {
                AppManager.getInstance().saveUser(user);
                EditProfileActivity.this.finish();
            }
        };
    }

    private void initToolbar() {
//        getSupportActionBar().set
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.person_center_menu, menu);
        MenuItem item = menu.findItem(R.id.confirm);
        item.setOnMenuItemClickListener(this);
        return super.onCreateOptionsMenu(menu);
    }

    private void initView() {
        User user = AppManager.getInstance().getUser();
        usernameEt.setText(user.getName());
//        passwordEt.setText(user.g());
        signtureEt.setText(user.getSignature());
        ImageUtil.setFrescoImageView(user.getCover(), coverSv);

        ImageUtil.setFrescoImageView(user.getAvatar(), profileSv);
    }

    @OnClick({R.id.profile_sv, R.id.cover_sv})
    public void onClick(View view) {
        MenuSheetView menuSheetView = null;
        switch (view.getId()) {
            case R.id.profile_sv:
                optionType = PROFILE_TYPE;
                menuSheetView = new MenuSheetView(EditProfileActivity.this, MenuSheetView.MenuType.LIST, getString(R.string.select), new MenuSheetView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(EditProfileActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (bottomSheetLayout.isSheetShowing()) {
                            bottomSheetLayout.dismissSheet();
                        }
                        if (item.getTitle().equals(getString(R.string.camera))) {
                            goCamera();
                        } else {
                            goAlbum();
                        }

                        return true;
                    }
                });
                menuSheetView.inflateMenu(R.menu.camera_photo);
                bottomSheetLayout.showWithSheetView(menuSheetView);
                break;
            case R.id.cover_sv:
                optionType = COVER_TYPE;
                menuSheetView = new MenuSheetView(EditProfileActivity.this, MenuSheetView.MenuType.LIST, getString(R.string.select), new MenuSheetView.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(EditProfileActivity.this, item.getTitle(), Toast.LENGTH_SHORT).show();
                        if (bottomSheetLayout.isSheetShowing()) {
                            bottomSheetLayout.dismissSheet();
                        }
                        if (item.getTitle().equals(getString(R.string.camera))) {
                            goCamera();
                        } else {
                            goAlbum();
                        }

                        return true;
                    }
                });
                menuSheetView.inflateMenu(R.menu.camera_photo);
                bottomSheetLayout.showWithSheetView(menuSheetView);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityForResultUtils.onActivityResult(requestCode, resultCode, data);
    }

    private void goAlbum() {
        final String photoName = FileUtils.getGeneratePhotoName();
        final String localFullPath = FileUtils.getPhotoFullPath(photoName);
        ActivityForResultUtils.startActivityForResult(this, ActivityResultConstants.ALBUM_CODE, IntentUtils.getSystemDefaultAlbumCropIntent(localFullPath), new SimpleOnActivityForResultCallback() {
            @Override
            public void success(Integer resultCode, Intent data) {
                try {
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    FileUtils.inputStream2File(inputStream, new File(localFullPath));
                    cropImg(photoName, localFullPath);
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
                cropImg(photoName, localFullPath);
            }
        });
    }


    private void cropImg(final String photoName, final String localPath) {
        Intent systemDefaultCropIntent = IntentUtils.getSystemDefaultCropIntent(localPath);
        ActivityForResultUtils.startActivityForResult(this, ActivityResultConstants.CROP_CODE, systemDefaultCropIntent, new SimpleOnActivityForResultCallback() {
            @Override
            public void success(Integer resultCode, Intent data) {
                if (optionType == PROFILE_TYPE) {
                    AlipayOSSClient.getInstance().uploadAndSetImageView(photoName, localPath, profileSv);
                    avatarPath = photoName;
                } else {
                    AlipayOSSClient.getInstance().uploadAndSetImageView(photoName, localPath, coverSv);
                    coverPath = photoName;
                }


            }
        });
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        String avatar = android.text.TextUtils.isEmpty(avatarPath) ?
                AppManager.getInstance().getUser().getAvatar() : avatarPath;
        String cover = android.text.TextUtils.isEmpty(coverPath) ?
                AppManager.getInstance().getUser().getAvatar() : coverPath;

        HttpMethods.getInstance().updateUser(new ProgressSubscriber<User>(updateSub, this), signtureEt.getText().toString(),
                usernameEt.getText().toString(), avatar, cover, passwordEt.getText().toString()
        );
        return false;
    }
}
