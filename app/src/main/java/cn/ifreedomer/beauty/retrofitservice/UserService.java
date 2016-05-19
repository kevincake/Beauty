package cn.ifreedomer.beauty.retrofitservice;

import java.util.Map;

import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.entity.User;
import cn.ifreedomer.beauty.entity.jsonbean.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * @author:eavawu
 * @date: 5/17/16.
 * @todo:
 */
public interface UserService {
    @GET(HttpConstants.UPDATE_USER)
    Observable<HttpResult<User>> updateUser(@QueryMap Map<String,String> userParams);
}
