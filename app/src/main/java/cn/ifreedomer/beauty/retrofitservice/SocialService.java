package cn.ifreedomer.beauty.retrofitservice;

import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.entity.jsonbean.HttpResult;
import cn.ifreedomer.beauty.entity.jsonbean.Like;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailList;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author:eavawu
 * @date: 5/14/16.
 * @todo:
 */
public interface SocialService {
    @GET(HttpConstants.GET_SOCIALS)
    Observable<HttpResult<SocialDetailList>> getSocialDetails();

    @POST(HttpConstants.LIKE_SOCIAL)
    Observable<HttpResult<Like>> postLike(@Query(HttpConstants.STATUS) int status, @Query(HttpConstants.SOCIAL_ID)long socialId);
    @POST(HttpConstants.GET_MINE_SOCIAL)
    Observable<HttpResult<SocialDetailList>> getMineSocial();
    @POST(HttpConstants.DEPLOY_SOCIAL)
    Observable<HttpResult<SocialDetailBean>> deploySocial(@Query("content") String content, @Query("type") int type,@Query("pic") String[] pic);

}
