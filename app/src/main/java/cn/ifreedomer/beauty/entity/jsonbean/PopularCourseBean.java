package cn.ifreedomer.beauty.entity.jsonbean;

import java.io.Serializable;

import cn.ifreedomer.beauty.entity.Course;
import cn.ifreedomer.beauty.entity.User;

/**
 * @atuhor:eavawu
 * @date:5/6/16
 * @todo:
 */
public class PopularCourseBean implements Serializable{
    private Course course;
    private int isFollow;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }
}
