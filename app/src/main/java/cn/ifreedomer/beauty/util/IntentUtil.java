package cn.ifreedomer.beauty.util;

import android.content.Context;
import android.content.Intent;

import cn.ifreedomer.beauty.activity.sign.SignSelectActivity;

/**
 * @author:eavawu
 * @date: 4/28/16.
 * @todo:跳转的Util
 */
public class IntentUtil {

    public static void startSignSelectActivity(Context context) {
        Intent intent = new Intent(context,SignSelectActivity.class);
        context.startActivity(intent);
    }
}
