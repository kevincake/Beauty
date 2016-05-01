package cn.ifreedomer.beauty.manager;

import cn.ifreedomer.beauty.constants.HawkConstants;
import cn.ifreedomer.beauty.entity.User;

/**
 * @author:eavawu
 * @date: 5/1/16.
 * @todo:
 */
public class AppManager {
    private static AppManager instance = new AppManager();
    private static DataCacheManager dataCacheManager = DataCacheManager.getInstance();
    public static AppManager getInstance() {
        return instance;
    }

    public void saveUser(User user){
        dataCacheManager.put( HawkConstants.USER,user);
    }

    public User getUser(){
       return (User) dataCacheManager.get(HawkConstants.USER);
    }


}
