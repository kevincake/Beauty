package cn.ifreedomer.beauty.util;

import android.widget.TextView;

import cn.ifreedomer.beauty.BeautyApplication;

/**
 * @author:eavawu
 * @date: 5/8/16.
 * @todo:
 */
public class TextWrapUtils {
    public static  void setWrapTime(TextView tv,long time,int resId){
        tv.setText(String.format(BeautyApplication.getInstance().getString(resId),DateUtil.getTimeStrBySecond(time)));
    }
}
