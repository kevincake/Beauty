package cn.ifreedomer.beauty.network;


import java.util.concurrent.TimeUnit;

import cn.ifreedomer.beauty.entity.HttpResult;
import cn.ifreedomer.beauty.entity.IsPhoneRegister;
import cn.ifreedomer.beauty.retrofitservice.SignService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by liukun on 16/3/9.
 */
public class HttpMethods {

    public static final String BASE_URL = "http://192.168.1.107:8080/";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private SignService signService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        signService = retrofit.create(SignService.class);
    }

    //在访问HttpMethods时创建单例
    private static class SingletonHolder{
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取注册码
     * @param subscriber  由调用者传过来的观察者对象
     * @param contryCode 国家码
     *
     */
    public void getIsPhoneRegister(Subscriber<IsPhoneRegister> subscriber, String contryCode){



        Observable observable = signService.getIsPhoneRegister(contryCode)
                .map(new HttpResultFunc<IsPhoneRegister>());
        toSubscribe(observable, subscriber);
    }

    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
         o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>,T>{

        @Override
        public T call(HttpResult<T> httpResult) {
            if (httpResult.getResultCode() == 0) {
                throw new ApiException(100);
            }
            return httpResult.getData() ;
        }
    }

}
