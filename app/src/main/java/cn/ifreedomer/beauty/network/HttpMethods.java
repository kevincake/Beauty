package cn.ifreedomer.beauty.network;


import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import cn.ifreedomer.beauty.activity.base.ActivityStackManager;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.entity.User;
import cn.ifreedomer.beauty.entity.jsonbean.ArticleListResult;
import cn.ifreedomer.beauty.entity.jsonbean.CourseItems;
import cn.ifreedomer.beauty.entity.jsonbean.FollowListResult;
import cn.ifreedomer.beauty.entity.jsonbean.HttpResult;
import cn.ifreedomer.beauty.entity.jsonbean.IsPhoneRegister;
import cn.ifreedomer.beauty.entity.jsonbean.Like;
import cn.ifreedomer.beauty.entity.jsonbean.LogInResult;
import cn.ifreedomer.beauty.entity.jsonbean.CourseList;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailList;
import cn.ifreedomer.beauty.manager.AppManager;
import cn.ifreedomer.beauty.retrofitservice.ArticleService;
import cn.ifreedomer.beauty.retrofitservice.CourseService;
import cn.ifreedomer.beauty.retrofitservice.FollowService;
import cn.ifreedomer.beauty.retrofitservice.SignService;
import cn.ifreedomer.beauty.retrofitservice.SocialService;
import cn.ifreedomer.beauty.retrofitservice.UserService;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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

//    public static final String BASE_URL = "http://115.28.38.189:8080/Beauty/";
public static final String BASE_URL = "http://192.168.1.107:8080/";
    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private SignService signService;
    private CourseService courseService;
    private FollowService followService;
    private SocialService socialService;
    private UserService userService;
    private ArticleService articleService;

    //构造方法私有
    private HttpMethods() {
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        builder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                if (AppManager.getInstance().isLogin()) {
                    Request.Builder requstbuilder = request.newBuilder().addHeader(HttpConstants.TOKEN, AppManager.getInstance().getUser().getId() + "_" + AppManager.getInstance().getToken());
                    request = requstbuilder.build();
                }

                return chain.proceed(request);
            }
        });
        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())

                .baseUrl(BASE_URL)
                .build();

        signService = retrofit.create(SignService.class);
        courseService = retrofit.create(CourseService.class);
        followService = retrofit.create(FollowService.class);
        socialService = retrofit.create(SocialService.class);
        userService = retrofit.create(UserService.class);
        articleService = retrofit.create(ArticleService.class);
    }

    public void sendFollowReq(int followStatus, Long id) {

    }


    //在访问HttpMethods时创建单例
    private static class SingletonHolder {
        private static final HttpMethods INSTANCE = new HttpMethods();
    }

    //获取单例
    public static HttpMethods getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取注册码
     *
     * @param subscriber 由调用者传过来的观察者对象
     * @param contryCode 国家码
     */
    public void getIsPhoneRegister(Subscriber<IsPhoneRegister> subscriber, String contryCode) {


        Observable observable = signService.getIsPhoneRegister(contryCode)
                .map(new HttpResultFunc<IsPhoneRegister>());
        toSubscribe(observable, subscriber);
    }

    /**
     * @param subscriber
     * @param nickName   名字
     * @param avatar     头像
     * @param sex        性别
     * @param phone      手机号
     */
    public void postSignUp(Subscriber<LogInResult> subscriber, String nickName, String avatar, Integer sex, String phone, String password) {
        Map<String, String> params = new LinkedHashMap<>();
        params.put(HttpConstants.NAME, nickName);
        params.put(HttpConstants.SEX, sex + "");
        params.put(HttpConstants.PHONE, phone);
        params.put(HttpConstants.AVATAR, avatar);
        params.put(HttpConstants.PASSWORD, password);
        Observable observable = signService.postSignUp(params)
                .map(new HttpResultFunc<LogInResult>());
        toSubscribe(observable, subscriber);
    }


    public void getSignIn(Subscriber<LogInResult> subscriber, String phone, String password) {
        Observable observable = signService.getSignIn(phone, password)
                .map(new HttpResultFunc<LogInResult>());
        toSubscribe(observable, subscriber);
    }

    //==================Course=====================
    public void getCourseItems(Subscriber<CourseItems> subscriber, long courseId) {
        Observable observable = courseService.getCourseItems(courseId)
                .map(new HttpResultFunc<CourseItems>());
        toSubscribe(observable, subscriber);
    }

    public void getPopularCourseList(Subscriber<CourseList> subscriber, int pageIndex) {
        Observable observable = courseService.getPopularCourseList(pageIndex)
                .map(new HttpResultFunc<CourseList>());
        toSubscribe(observable, subscriber);
    }

    public void getLikeCourseList(Subscriber<CourseList> subscriber, int pageIndex) {
        Observable observable = courseService.getLikeCourse(pageIndex)
                .map(new HttpResultFunc<CourseList>());
        toSubscribe(observable, subscriber);
    }


    //==================FOLLOW===================
    public void postFollowStatus(Subscriber subscriber, long userId, int followStatus) {
        Observable observable = followService.postFollowStatus(userId, followStatus)
                .map(new HttpResultFunc());
        toSubscribe(observable, subscriber);
    }


    public void getFollowList(Subscriber<FollowListResult> subscriber) {
        Observable observable = followService.getFollowList()
                .map(new HttpResultFunc<FollowListResult>());
        toSubscribe(observable, subscriber);
    }

    //=====================social===============

    public void deployComment(Subscriber<SocialDetailBean> subscriber, String content, int type ,String[] pic) {
        Observable observable = socialService.deploySocial(content, type ,pic)
        .map(new HttpResultFunc<SocialDetailBean>());
        toSubscribe(observable, subscriber);
    }


    public void getSocialDetails(Subscriber<SocialDetailList> subscriber) {
        Observable observable = socialService.getSocialDetails()
                .map(new HttpResultFunc<SocialDetailList>());
        toSubscribe(observable, subscriber);
    }


    public void getMineSocial(Subscriber<SocialDetailList> subscriber) {
        Observable observable = socialService.getMineSocial()
                .map(new HttpResultFunc<SocialDetailList>());
        toSubscribe(observable, subscriber);
    }


    public void postLikeStatus(Subscriber<Like> subscriber, long socialId, int status) {
        Observable observable = socialService.postLike(status, socialId)
                .map(new HttpResultFunc<Like>());
        toSubscribe(observable, subscriber);
    }

    //====================Article==================
    public void getPopularArticleList(Subscriber<ArticleListResult> subscriber, int pageIndex) {
        Observable observable = articleService.getPopularArticleList(pageIndex)
                .map(new HttpResultFunc<ArticleListResult>());
        toSubscribe(observable, subscriber);
    }


    //====================User=====================
    public void updateUser(Subscriber<User> subscriber, String signture, String nickName, String avatar, String cover, String password) {

        Map<String, String> params = new LinkedHashMap<>();
        params.put(HttpConstants.NAME, nickName);
        params.put(HttpConstants.AVATAR, avatar);
        params.put(HttpConstants.COVER, cover);
        params.put(HttpConstants.PASSWORD, password);
        params.put(HttpConstants.SIGNTURE, signture);
        Observable observable = userService.updateUser(params)
                .map(new HttpResultFunc<User>());
        toSubscribe(observable, subscriber);
    }


    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s) {
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }


    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T> Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

        @Override
        public T call(final HttpResult<T> httpResult) {
            if (httpResult.getResultCode() == 0) {
                //在activity里面设置提示
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        BaseActivity baseActivity = (BaseActivity) ActivityStackManager.getScreenManager().currentActivity();
                        baseActivity.showErrorMsg(httpResult.getMsg());
                    }
                });
                return null;

            } else {

                return httpResult.getData();
            }

        }
    }

}
