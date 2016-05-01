package cn.ifreedomer.beauty.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import cn.ifreedomer.beauty.R;

/**
 * @author:eavawu
 * @date: 4/29/16.
 * @todo:
 */
public class TimeView extends TextView {
    private Context ctx;
    private Timer mTimer;
    private int maxTime = 60;
    private int curTime = maxTime;

    public TimeView(Context context) {
        super(context);
        ctx = context;
    }

    /**
     * @param time max count time. default is 60s
     */
    public void setCountTime(int time){
        maxTime = time;
        curTime = maxTime;
    }

    public TimeView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
    }

    public void beginCountDown() {
        mTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {

                Activity activity = (Activity) ctx;
                activity.runOnUiThread(new Runnable() {      // UI thread
                    @Override
                    public void run() {
                        curTime--;
                        TimeView.this.setText(curTime + "s");
                        if (curTime < 0) {
                            TimeView.this.setText(getResources().getString(R.string.getcode_text));
                            mTimer.cancel();
                            TimeView.this.setClickable(true);
                            mTimer = null;
                            curTime=maxTime;
                        }
                    }
                });
            }
        };
        setClickable(false);
        mTimer.schedule(task, 0, 1000);
    }
}
