package cn.ifreedomer.beauty.entity.jsonbean;

import java.util.List;

/**
 * @author:eavawu
 * @date: 5/7/16.
 * @todo:
 */
public class CourseList {
    List<PopularCourseBean> courseList;

    public List<PopularCourseBean> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<PopularCourseBean> courseList) {
        this.courseList = courseList;
    }
}
