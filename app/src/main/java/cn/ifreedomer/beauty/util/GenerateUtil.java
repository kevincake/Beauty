package cn.ifreedomer.beauty.util;

import java.util.Random;

/**
 * 创建者:eava
 * 创建时间:2016/3/18 16:02
 * 功能说明:
 */
public class GenerateUtil {
    // 62
    public static final String[] LETTERS = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "", "A", "B", "C", "D", "E", "F", "G", "H", "I",
            "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V",
            "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    private static final int NUM_SIZE = 6;
    /**
     * 创建者: eava
     * 创建时间: 2016/3/9 19:23
     * 功能说明: 文件名，tempMsgId
     */
    public static String getGenerateName() {
        String time = System.currentTimeMillis() + "";
        StringBuffer sixLetter = new StringBuffer();
        for (int i = 0; i < NUM_SIZE; i++) {
            int randomNum = new Random().nextInt(LETTERS.length);
            sixLetter.append(LETTERS[randomNum]);
        }
        String md5Result = MD5Utils.getMD5((time + sixLetter));
        return md5Result;
    }

}
