package cn.ifreedomer.beauty.notifycation;

import org.greenrobot.eventbus.EventBus;

/**
 * @author:eavawu
 * @date: 5/1/16.
 * @todo:通知的封装
 */
public class NotifycationManager {
    private static NotifycationManager instance = new NotifycationManager();

    public static NotifycationManager getInstance() {
        return instance;
    }

    public void register(Object object) {
        EventBus.getDefault().register(object);
    }

    public void unregister(Object object) {
        EventBus.getDefault().unregister(object);
    }

    public void post(Object object) {
        EventBus.getDefault().post(object);
    }
}
