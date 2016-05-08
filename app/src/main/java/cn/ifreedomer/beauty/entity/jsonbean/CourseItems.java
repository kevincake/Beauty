package cn.ifreedomer.beauty.entity.jsonbean;

import java.util.List;

import cn.ifreedomer.beauty.entity.CourseItem;

/**
 * @author:eavawu
 * @date: 5/8/16.
 * @todo:
 */
public class CourseItems {
    public List<CourseItem> getCourseItemList() {
        return courseItemList;
    }

    public void setCourseItemList(List<CourseItem> courseItemList) {
        this.courseItemList = courseItemList;
    }

    List<CourseItem> courseItemList;
}
