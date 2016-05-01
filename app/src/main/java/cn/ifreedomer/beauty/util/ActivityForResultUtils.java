package cn.ifreedomer.beauty.util;

import android.app.Activity;
import android.content.Intent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.ifreedomer.beauty.callback.OnActivityForResultCallback;


public class ActivityForResultUtils {
    public static Map<Integer, List<OnActivityForResultCallback>> resultMap = new HashMap<Integer, List<OnActivityForResultCallback>>();

    public static void startActivityForResult(Activity activity, Integer requestCode, Intent intent, OnActivityForResultCallback callback) {
        List<OnActivityForResultCallback> callbackList = resultMap.get(requestCode);
        if (callbackList == null||callbackList.size()<=0) {
            callbackList = new ArrayList<OnActivityForResultCallback>();
            resultMap.put(requestCode, callbackList);
        }else{
            callbackList.clear();
        }
        resultMap.get(requestCode).add(0, callback);
        activity.startActivityForResult(intent, requestCode);
    }

    public static void onActivityResult(int requestCode, int resultCode, Intent data) {
        List<OnActivityForResultCallback> callbackList = resultMap.get(requestCode);
        if (null != callbackList) {
            for (OnActivityForResultCallback callback : callbackList) {
                if (Activity.RESULT_CANCELED == resultCode) {
                    callback.cancel(data);
                } else {
                    callback.success(resultCode, data);
                }
                callback.result(resultCode, data);
            }
        }
    }
}
