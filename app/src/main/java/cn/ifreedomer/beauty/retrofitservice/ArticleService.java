package cn.ifreedomer.beauty.retrofitservice;

import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.entity.jsonbean.ArticleListResult;
import cn.ifreedomer.beauty.entity.jsonbean.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author:eavawu
 * @date: 5/19/16.
 * @todo:
 */
public interface ArticleService{
        @GET("article/getPopularArticle")
        Observable<HttpResult<ArticleListResult>> getPopularArticleList(@Query(HttpConstants.PAGEINDEX)int pageIndex);
}
