package cn.ifreedomer.beauty;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tencent.bugly.crashreport.CrashReport;

import cn.ifreedomer.beauty.constants.Constants;
import cn.ifreedomer.beauty.manager.DataCacheManager;
import cn.ifreedomer.beauty.util.FileUtils;
import cn.smssdk.SMSSDK;

/**
 * @author:eavawu
 * @date: 4/28/16.
 * @todo:
 */
public class BeautyApplication extends Application {
    private static BeautyApplication instance;

    public static BeautyApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化bugly
        initBugly();
        initFresco();
//        initSMSSDK();
        initFolder();
        initDataCache();
    }

    private void initDataCache() {
        DataCacheManager.getInstance().initDataCacheManager();
    }

    private void initFolder() {
        FileUtils.initCacheFolder();
    }

    private void initSMSSDK() {
        SMSSDK.initSDK(instance, Constants.SMS_APPKEY, Constants.SMS_SECRET, true);
    }

    private void initFresco() {
        Fresco.initialize(instance);
    }

    public void initBugly() {
        CrashReport.initCrashReport(getApplicationContext());
//        CrashReport.testJavaCrash();

    }

}
