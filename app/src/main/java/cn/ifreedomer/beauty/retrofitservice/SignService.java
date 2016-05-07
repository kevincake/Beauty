package cn.ifreedomer.beauty.retrofitservice;

import java.util.Map;

import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.entity.HttpResult;
import cn.ifreedomer.beauty.entity.IsPhoneRegister;
import cn.ifreedomer.beauty.entity.LogInResult;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author:eavawu
 * @date: 4/30/16.
 * @todo:登陆和注册模块的service
 *
 */
public interface SignService {

    @GET(HttpConstants.ISPHONE_REGISTER)
    Observable<HttpResult<IsPhoneRegister>> getIsPhoneRegister(@Query("phone")String phone);

    @POST(HttpConstants.SIGN_UP)
    Observable<HttpResult<LogInResult>> postSignUp(@QueryMap Map<String,String> userParams);

    @GET(HttpConstants.SIGN_IN)
    Observable<HttpResult<LogInResult>> getSignIn(@Query(HttpConstants.PHONE)String account, @Query(HttpConstants.PASSWORD)String password);



}
