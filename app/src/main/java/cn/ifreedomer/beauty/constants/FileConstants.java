package cn.ifreedomer.beauty.constants;

import cn.ifreedomer.beauty.BeautyApplication;
import cn.ifreedomer.beauty.util.FileUtils;

/**
 * @author:eavawu
 * @date: 4/30/16.
 * @todo:
 */
public class FileConstants {
    public static final String VIDEO_PATH = FileUtils.getCacheDir(BeautyApplication.getInstance())+ "/video/";
    public static final String PHOTO_PATH = FileUtils.getCacheDir(BeautyApplication.getInstance())+"/photo/";
    public static final String VOICE_PATH = FileUtils.getCacheDir(BeautyApplication.getInstance())+ "/voice/";
}
