package cn.ifreedomer.beauty.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 创建者:yin_zeping
 * 创建时间:2016/3/1 14:22
 * 功能说明:
 */
public class StringUtils {
    //用正则表达式判断字符串是否为数字（含负数）
    public static boolean isNumeric(String str) {
        String regEx = "^-?[0-9]+$";
        Pattern pat = Pattern.compile(regEx);
        Matcher mat = pat.matcher(str);
        if (mat.find()) {
            return true;
        } else {
            return false;
        }
    }


    public static boolean isPhone(String phone) {
        if(TextUtils.isEmpty(phone)) {
            return false;
        }
        boolean matches = phone.matches("(^(13\\d|14[57]|15[^4,\\D]|17[678]|18\\d)\\d{8}|170[059]\\d{7})$");
        return matches;
    }


    public static boolean isContainCode(String number) {
        if (number==null){
            return false;
        }
        boolean b = number.startsWith("+86");
        return b;
    }
    public static String getPhoneFromCountyPhoneNum(String number){
        if (isContainCode(number)){
            StringBuffer buffer = new StringBuffer(number);
            String result = buffer.substring(3, number.length()).trim();
            return result;
        }
        return  "";
    }
}
