package cn.ifreedomer.beauty.retrofitservice;

import cn.ifreedomer.beauty.entity.HttpResult;
import cn.ifreedomer.beauty.entity.PoplarList;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * @author:eavawu
 * @date: 5/6/16.
 * @todo:课程的service
 */
public interface CourseService {
    @GET("course")
    Observable<HttpResult<PoplarList>> getPopularCourseList(@Query("pageIndex")int pageIndex);

}
