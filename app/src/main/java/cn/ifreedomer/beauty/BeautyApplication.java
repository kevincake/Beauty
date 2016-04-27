package cn.ifreedomer.beauty;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * @author:eavawu
 * @date: 4/28/16.
 * @todo:
 */
public class BeautyApplication extends Application{
    private BeautyApplication instance;
    public BeautyApplication getInstance(){
        return instance;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化bugly
        initBugly();
    }
    public void initBugly(){
        CrashReport.initCrashReport(getApplicationContext());
//        CrashReport.testJavaCrash();

    }

}
