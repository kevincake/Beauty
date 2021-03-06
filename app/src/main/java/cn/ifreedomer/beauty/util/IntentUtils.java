package cn.ifreedomer.beauty.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

import cn.ifreedomer.beauty.activity.ArticleDetailActivity;
import cn.ifreedomer.beauty.activity.CommentActivity;
import cn.ifreedomer.beauty.activity.CourseDetailInfoActivity;
import cn.ifreedomer.beauty.activity.DeployCommentActivity;
import cn.ifreedomer.beauty.activity.DeployCourseActivity;
import cn.ifreedomer.beauty.activity.MainActivity;
import cn.ifreedomer.beauty.activity.PhotoPreviewActivity;
import cn.ifreedomer.beauty.activity.VideoPlayerActivity;
import cn.ifreedomer.beauty.activity.personcenter.EditProfileActivity;
import cn.ifreedomer.beauty.activity.personcenter.SettingActivity;
import cn.ifreedomer.beauty.activity.shop.GoodsListActivity;
import cn.ifreedomer.beauty.activity.sign.SignInActivity;
import cn.ifreedomer.beauty.activity.sign.SignSelectActivity;
import cn.ifreedomer.beauty.activity.sign.SignUpActivity;
import cn.ifreedomer.beauty.activity.sign.SignUpFullInfoActivity;
import cn.ifreedomer.beauty.constants.ImageConstants;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.entity.jsonbean.ArticleCommon;
import cn.ifreedomer.beauty.entity.jsonbean.PopularCourseBean;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;

/**
 * @author:eavawu
 * @date: 4/28/16.
 * @todo:跳转的Util
 */
public class IntentUtils {

    public static void startSignSelectActivity(Activity context) {
        Intent intent = new Intent(context, SignSelectActivity.class);
        context.startActivity(intent);
        context.finish();
    }

    public static void startSignInActivity(Activity context) {
        Intent intent = new Intent(context, SignInActivity.class);
        context.startActivity(intent);
    }

    public static void startSignUpActivity(Activity context) {
        Intent intent = new Intent(context, SignUpActivity.class);
        context.startActivity(intent);
    }

    public static void startSignUpFullInfoActivity(String phone, Activity context) {
        Intent intent = new Intent(context, SignUpFullInfoActivity.class);
        intent.putExtra(IntentConstants.PHONE, phone);
        context.startActivity(intent);
    }

    public static Intent getSystemDefaultCameraIntent(String localPath) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);//action is capture
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(localPath)));
        return intent;
    }

    public static Intent getSystemDefaultAlbumCropIntent(String localPath) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.parse(localPath));
        return intent;
    }

    public static Intent getSystemDefaultCropIntent(String localPath) {
        Uri uri = Uri.fromFile(new File(localPath));
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("CROP", "true");
        intent.putExtra("aspectX", ImageConstants.ASPECT_X);
        intent.putExtra("aspectY", ImageConstants.ASPECT_Y);
        intent.putExtra("outputX", ImageConstants.OUTPUT_X);
        intent.putExtra("outputY", ImageConstants.OUTPUT_Y);
        intent.putExtra("scale", true);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
        intent.putExtra("return-data", false);
        intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
        intent.putExtra("noFaceDetection", true); // no face detection
        return intent;
    }


    public static void startMainActivity(Activity context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public static void startCourseDetailActivity(Activity ctx, PopularCourseBean popularCourseBean) {
        Intent intent = new Intent(ctx, CourseDetailInfoActivity.class);
        intent.putExtra(IntentConstants.POPCOURSE_BEAN, popularCourseBean);
        ctx.startActivity(intent);
    }

    public static void startVideoPlayerActivity(Activity ctx, String url) {
        Intent intent = new Intent(ctx, VideoPlayerActivity.class);
        intent.putExtra(IntentConstants.VIDEO_URL, url);
        ctx.startActivity(intent);
    }

    public static void startCommentActivity(Activity ctx, SocialDetailBean socialDetailBean) {
        Intent intent = new Intent(ctx, CommentActivity.class);
        intent.putExtra(IntentConstants.SOCIAL_BEAN, socialDetailBean);
        ctx.startActivity(intent);

    }

    public static void startEditProfileActivity(Activity ctx) {
        Intent intent = new Intent(ctx, EditProfileActivity.class);
        ctx.startActivity(intent);

    }

    public static void startSettingActivity(Activity ctx) {
        Intent intent = new Intent(ctx, SettingActivity.class);
        ctx.startActivity(intent);
    }

    public static void startArticleDetailActivity(Activity ctx, ArticleCommon article) {
        Intent intent = new Intent(ctx, ArticleDetailActivity.class);
        intent.putExtra(IntentConstants.ARTICLE_COMMON, article);
        ctx.startActivity(intent);
    }

    public static void startPreviewActivity(Activity ctx, String url, int type) {
        Intent intent = new Intent(ctx, PhotoPreviewActivity.class);
        intent.putExtra(IntentConstants.BITMAP_URL, url);
        intent.putExtra(IntentConstants.URI_TYPE, type);
        ctx.startActivity(intent);
    }

    public static void startDeployCommentActivity(Activity ctx) {
        Intent intent = new Intent(ctx, DeployCommentActivity.class);
        ctx.startActivity(intent);
    }

    public static void startDeployCourseActivity(Activity ctx) {
        Intent intent = new Intent(ctx, DeployCourseActivity.class);
        ctx.startActivity(intent);
    }

    public static void startGoodListActivity(Context context) {
        context.startActivity(new Intent(context, GoodsListActivity.class));
    }
//
//    public static Bundle getSocialBundler(int type){
//
//    };

}
