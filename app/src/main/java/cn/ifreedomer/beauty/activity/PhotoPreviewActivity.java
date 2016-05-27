package cn.ifreedomer.beauty.activity;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.os.Bundle;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

import java.io.File;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.constants.IntentConstants;
import me.relex.photodraweeview.PhotoDraweeView;

public class PhotoPreviewActivity extends BaseActivity {

    public static final int NETWORK = 1;
    public static final int LOCAL = 0;


    @Bind(R.id.center_iv)
    PhotoDraweeView mPhotoDraweeView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_preview);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        String bitmapUrl = getIntent().getStringExtra(IntentConstants.BITMAP_URL);
        int urlType = getIntent().getIntExtra(IntentConstants.URI_TYPE,NETWORK);
        PipelineDraweeControllerBuilder controller = Fresco.newDraweeControllerBuilder();
        if (urlType==NETWORK){
            controller.setUri(bitmapUrl);
        }else{
            controller.setUri(Uri.fromFile(new File(bitmapUrl)));
        }

        controller.setOldController(mPhotoDraweeView.getController());
        controller.setControllerListener(new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);
                if (imageInfo == null || mPhotoDraweeView == null) {
                    return;
                }
                mPhotoDraweeView.update(imageInfo.getWidth(), imageInfo.getHeight());
            }
        });
        mPhotoDraweeView.setController(controller.build());


//        Bitmap bitmap = getIntent().getParcelableExtra(IntentConstants.BITMAP);
    }
}
