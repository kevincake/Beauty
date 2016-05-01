package cn.ifreedomer.beauty.retrofitservice;

import cn.ifreedomer.beauty.entity.HttpResult;
import cn.ifreedomer.beauty.entity.IsPhoneRegister;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author:eavawu
 * @date: 4/30/16.
 * @todo:登陆和注册模块的service
 *
 */
public interface SignService {

    public static final String SIGN = "sign/";
    @GET(SIGN+"isPhoneRegister")
    Observable<HttpResult<IsPhoneRegister>> getIsPhoneRegister(@Query("phone")String phone);
}
