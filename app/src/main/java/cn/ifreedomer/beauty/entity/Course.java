package cn.ifreedomer.beauty.entity;

import java.io.Serializable;

/**
 * @author:eavawu
 * @date: 5/6/16.
 * @todo:
 */
public class Course implements Serializable{
    private String courseName;
    private Long userId;
    private int type;
    private String pic[];
    private Integer popular;
    private Long deployTime;
    private Long courseTime;
    private String courseDes;
    private int joinMan;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private long id;

    public Long getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(Long courseTime) {
        this.courseTime = courseTime;
    }

    public String getCourseDes() {
        return courseDes;
    }

    public void setCourseDes(String courseDes) {
        this.courseDes = courseDes;
    }

    public int  getJoinMan() {
        return  joinMan;
    }

    public void setJoinMan(int joinMan) {
        this.joinMan = joinMan;
    }


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String[] getPic() {
        return pic;
    }

    public void setPic(String[] pic) {
        this.pic = pic;
    }

    public Integer getPopular() {
        return popular;
    }

    public void setPopular(Integer popular) {
        this.popular = popular;
    }

    public Long getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Long deployTime) {
        this.deployTime = deployTime;
    }


}
