package cn.ifreedomer.beauty.retrofitservice;

import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.entity.jsonbean.FollowListResult;
import cn.ifreedomer.beauty.entity.jsonbean.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author:eavawu
 * @date: 5/7/16.
 * @todo:更新获取follow的状态
 */
public interface FollowService {

    @POST(HttpConstants.FOLLOW_USER_PATH)
    Observable<HttpResult> postFollowStatus(@Path(value = HttpConstants.USERID)Long userId, @Query(value = HttpConstants.FOLLOW_STATUS_KEY)int followStatus);
    @GET(HttpConstants.GET_FOLLOWLIST)
    Observable<HttpResult<FollowListResult>> getFollowList();

}
