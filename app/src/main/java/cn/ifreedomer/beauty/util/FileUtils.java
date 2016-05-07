package cn.ifreedomer.beauty.util;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import cn.ifreedomer.beauty.constants.FileConstants;

/**
 * @author:eavawu
 * @date: 4/30/16.
 * @todo:
 */
public class FileUtils {
    public static String getCacheDir(Context context) {
        String cacheDir;
        cacheDir =  Environment.getExternalStorageDirectory()+"/beauty";
//        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) && context.getExternalCacheDir() != null) {
//            //如果SD卡存在，取SD卡的应用缓存目录
//            cacheDir = context.getExternalCacheDir().getPath();
//        } else {
//            //如果SD卡不存在，取应用缓存目录
//            cacheDir = context.getCacheDir().getPath();
//        }
        return cacheDir;
    }

    public static void initCacheFolder() {
        File videoFile = new File(FileConstants.VIDEO_PATH);
        File voiceFile = new File(FileConstants.VOICE_PATH);
        File photoFile = new File(FileConstants.PHOTO_PATH);
        if (!videoFile.exists()) {
            videoFile.mkdirs();
        }
        if (!voiceFile.exists()) {
            voiceFile.mkdirs();
        }
        if (!photoFile.exists()) {
            photoFile.mkdirs();
        }
    }

    public static String getGeneratePhotoName() {
        return  GenerateUtil.getGenerateName() + ".jpg";
    }

    public static String getGenerateVideoName() {
        return GenerateUtil.getGenerateName() + ".mp4";
    }

    public static String getGenerateVoiceName() {
        return GenerateUtil.getGenerateName() + ".mp3";
    }

    public static String getPhotoFullPath(String photoName) {
        return FileConstants.PHOTO_PATH + photoName;
    }

    public static String getVideoFullPath(String videoName){
        return FileConstants.VIDEO_PATH+videoName;
    }

    public static String getVoiceFullPath(String voiceName){
        return FileConstants.VOICE_PATH+voiceName;
    }


    public static  void inputStream2File(InputStream ins, File file) {
//        File file = new File(path);
        OutputStream outputStream = null;
        try {

            outputStream = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] bytes = new byte[1024];
            int read = 0;
            while ((read = ins.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.close();
//            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
