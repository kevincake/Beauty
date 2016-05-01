package cn.ifreedomer.beauty.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author:eavawu
 * @date: 4/29/16.
 * @todo:auto switch viewpager
 */
public class AutoSwitchViewPager extends ViewPager{
    private static final String TAG = "AutoSwithPageView";
    private List<View> views = new ArrayList<>();
    private int delayTime = 3000;
    private int priodTime = 3000;
    private int SWITCH_WHAT = 1;
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (getCurrentItem() >= getAdapter().getCount() - 1) {
                setCurrentItem(0);
            } else if (getCurrentItem() <  getAdapter().getCount()) {
                setCurrentItem(getCurrentItem()+1);
            }
        }
    };

    public int getDelayTime() {
        return delayTime;
    }

    public void setDelayTime(int delayTime) {
        this.delayTime = delayTime;
    }

    public int getPriodTime() {
        return priodTime;
    }

    public void setPriodTime(int priodTime) {
        this.priodTime = priodTime;
    }

    public AutoSwitchViewPager(Context context) {
        super(context);
    }

    public AutoSwitchViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public void beginAutoSwitch() {
        if (getAdapter().getCount()<=0){
            Log.e(TAG,"please call addPage first");
            return;

        }
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(SWITCH_WHAT);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, delayTime, priodTime);
    }
}
