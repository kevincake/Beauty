package cn.ifreedomer.beauty.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.adapter.PhotoShowRvAdapter;
import cn.ifreedomer.beauty.callback.SimpleOnActivityForResultCallback;
import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.decorate.HorSpaceItemDecoration;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.oss.AlipayOSSClient;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.ActivityForResultUtils;
import cn.ifreedomer.beauty.util.DensityUtil;
import cn.ifreedomer.beauty.util.FileUtils;
import cn.ifreedomer.beauty.util.ToastUtil;
import cn.ifreedomer.beauty.widget.TipsDialog;
import me.nereo.multi_image_selector.MultiImageSelectorActivity;

public class DeployCommentActivity extends BaseActivity implements MenuItem.OnMenuItemClickListener, View.OnClickListener {
    private static final int REQUEST_IMAGE = 1;
    private int updateFinishCount = 0;
    private List<String> uploadUrls = new ArrayList<>();
    @Bind(R.id.content_et)
    EditText contentEt;
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    @Bind(R.id.addphoto_layout)
    View addPhotoLayout;
    private SubscriberOnNextListener<SocialDetailBean> deploySocialSub;
    private List<String> paths;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deploy_comment);
        ButterKnife.bind(this);
        initNetwork();
//        getActionBarToolbar();

//        initPhotoSelect();
    }

    private void initNetwork() {
        deploySocialSub = new SubscriberOnNextListener<SocialDetailBean>() {
            @Override
            public void onNext(SocialDetailBean socialDetailList) {

            }
        };
    }

    private void goSelectPic() {
        ArrayList<String> photos = new ArrayList<>();
        Intent intent = new Intent(this, MultiImageSelectorActivity.class);
        // whether show camera
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SHOW_CAMERA, true);
        // max select image amount
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_COUNT, 9);
        // select mode (MultiImageSelectorActivity.MODE_SINGLE OR MultiImageSelectorActivity.MODE_MULTI)
        intent.putExtra(MultiImageSelectorActivity.EXTRA_SELECT_MODE, MultiImageSelectorActivity.MODE_MULTI);
        // default select images (support array list)
        intent.putStringArrayListExtra(MultiImageSelectorActivity.EXTRA_DEFAULT_SELECTED_LIST, photos);

        ActivityForResultUtils.startActivityForResult(this, REQUEST_IMAGE, intent, new SimpleOnActivityForResultCallback() {
                    @Override
                    public void success(Integer resultCode, Intent data) {
                        //
                        if (resultCode == RESULT_OK) {
                            paths = data.getStringArrayListExtra(MultiImageSelectorActivity.EXTRA_RESULT);
                            PhotoShowRvAdapter photoShowRvAdapter = new PhotoShowRvAdapter(DeployCommentActivity.this, 0, paths);
                            // 创建一个线性布局管理器
                            LinearLayoutManager layoutManager = new LinearLayoutManager(DeployCommentActivity.this);
                            // 默认是Vertical，可以不写
                            layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
                            // 设置布局管理器
                            recycleview.addItemDecoration(new HorSpaceItemDecoration(DensityUtil.dip2px(DeployCommentActivity.this, getResources().getDimension(R.dimen.dimen_dp1))));
                            recycleview.setAdapter(photoShowRvAdapter);
                            recycleview.setLayoutManager(layoutManager);

//        }
                        }
                    }
                }
        );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ActivityForResultUtils.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.deploy_comment, menu);
        MenuItem item = menu.findItem(R.id.send);
//        item.setTitle("ahahahah");
        item.setOnMenuItemClickListener(this);
        return super.onCreateOptionsMenu(menu);
    }


    public void batchUploadImg() {
        uploadUrls.clear();
        showLoading(getString(R.string.upload_pic));
        for (int i = 0; i < paths.size(); i++) {
            final String generateFileName = FileUtils.getGeneratePhotoName();

            AlipayOSSClient.getInstance().uploadFileAsync(generateFileName, paths.get(i), new OSSCompletedCallback() {
                @Override
                public void onSuccess(OSSRequest ossRequest, OSSResult ossResult) {
                    updateFinishCount = updateFinishCount + 1;
                    uploadUrls.add(AlipayOSSClient.getInstance().getUrl(generateFileName));
                    if (updateFinishCount==paths.size()){
                        dissmissLoading();
                        HttpMethods.getInstance().deployComment(new ProgressSubscriber<SocialDetailBean>(deploySocialSub, DeployCommentActivity.this), contentEt.getText().toString(), HttpConstants.SOCIAL_TYPE, uploadUrls.toArray(new String[paths.size()]));
                        DeployCommentActivity.this.finish();
                    }
                }

                @Override
                public void onFailure(OSSRequest ossRequest, ClientException e, ServiceException e1) {
                    dissmissLoading();
                }
            });
        }

    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send:
            if (android.text.TextUtils.isEmpty( contentEt.getText().toString())){
                ToastUtil.showTextToast(this,getString(R.string.no_content));
                return false;
            }
            batchUploadImg();

                break;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (!android.text.TextUtils.isEmpty(contentEt.getText().toString())){
            TipsDialog.showTipDialog(this, getString(R.string.ignore_content_quit), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                        finish();
                }
            });
        }
//        super.onBackPressed();
    }

    @Override
    @OnClick({R.id.addphoto_layout})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addphoto_layout:
                goSelectPic();
                break;

        }
    }
}
