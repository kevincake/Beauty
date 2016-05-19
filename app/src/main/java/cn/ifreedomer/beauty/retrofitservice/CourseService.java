package cn.ifreedomer.beauty.retrofitservice;

import cn.ifreedomer.beauty.entity.jsonbean.CourseItems;
import cn.ifreedomer.beauty.entity.jsonbean.HttpResult;
import cn.ifreedomer.beauty.entity.jsonbean.CourseList;
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
    Observable<HttpResult<CourseList>> getPopularCourseList(@Query("pageIndex")int pageIndex);
    @GET("course/getCourseItems")
    Observable<HttpResult<CourseItems>> getCourseItems(@Query("courseId")long courseId);
    @GET("course/getLikeCourse")
    Observable<HttpResult<CourseList>> getLikeCourse(@Query("pageIndex")int pageIndex);


}
