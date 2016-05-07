package cn.ifreedomer.beauty.util;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import java.io.File;

import cn.ifreedomer.beauty.activity.CourseDetailInfoActivity;
import cn.ifreedomer.beauty.activity.MainActivity;
import cn.ifreedomer.beauty.activity.sign.SignInActivity;
import cn.ifreedomer.beauty.activity.sign.SignSelectActivity;
import cn.ifreedomer.beauty.activity.sign.SignUpActivity;
import cn.ifreedomer.beauty.activity.sign.SignUpFullInfoActivity;
import cn.ifreedomer.beauty.constants.ImageConstants;
import cn.ifreedomer.beauty.constants.IntentConstants;

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

    public static void startSignUpFullInfoActivity(String phone ,Activity context) {
        Intent intent = new Intent(context, SignUpFullInfoActivity.class);
        intent.putExtra(IntentConstants.PHONE,phone);
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

    public static Intent getSystemDefaultCropIntent(String localPath){
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
        Intent intent = new Intent(context,MainActivity.class);
        context.startActivity(intent);
    }

    public static void startCourseDetailActivity(Activity ctx) {
        Intent intent = new Intent(ctx, CourseDetailInfoActivity.class);
        ctx.startActivity(intent);
    }
}
