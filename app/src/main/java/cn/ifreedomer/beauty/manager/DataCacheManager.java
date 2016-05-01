package cn.ifreedomer.beauty.manager;

import com.orhanobut.hawk.Hawk;
import com.orhanobut.hawk.HawkBuilder;
import com.orhanobut.hawk.LogLevel;

import cn.ifreedomer.beauty.BeautyApplication;

/**
 * @author:eavawu
 * @date: 5/1/16.
 * @todo:
 */
public class DataCacheManager {
    private static DataCacheManager instance = new DataCacheManager();
    public static DataCacheManager getInstance(){
        return instance;
    }
    public  void initDataCacheManager(){
        Hawk.init(BeautyApplication.getInstance())
                .setEncryptionMethod(HawkBuilder.EncryptionMethod.MEDIUM)
                .setStorage(HawkBuilder.newSqliteStorage(BeautyApplication.getInstance()))
                .setLogLevel(LogLevel.FULL)
                .build();
    }

    public void put(String key,Object object){
        Hawk.chain().put(key,object).commit();
    }

    public Object get(String key){
      return  Hawk.get(key);
    }
}
