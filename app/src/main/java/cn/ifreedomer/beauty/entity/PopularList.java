package cn.ifreedomer.beauty.entity;

import java.util.List;

/**
 * @author:eavawu
 * @date: 5/7/16.
 * @todo:
 */
public class PopularList {

    /**
     * resultCode : 1
     * msg : null
     * data : {"courseList":[{"course":{"id":1,"courseName":"course0","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0},{"course":{"id":2,"courseName":"course1","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0},{"course":{"id":3,"courseName":"course2","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0},{"course":{"id":4,"courseName":"course3","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0},{"course":{"id":5,"courseName":"course4","userId":2,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0},{"course":{"id":6,"courseName":"course5","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0},{"course":{"id":7,"courseName":"course6","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0},{"course":{"id":8,"courseName":"course7","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0},{"course":{"id":9,"courseName":"course8","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0},{"course":{"id":10,"courseName":"course9","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null},"isFollow":0}]}
     */

    private int resultCode;
    private Object msg;
    private DataBean data;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getMsg() {
        return msg;
    }

    public void setMsg(Object msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * course : {"id":1,"courseName":"course0","userId":1,"type":1,"pic":["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"],"popular":1,"deployTime":null}
         * isFollow : 0
         */

        private List<CourseListBean> courseList;

        public List<CourseListBean> getCourseList() {
            return courseList;
        }

        public void setCourseList(List<CourseListBean> courseList) {
            this.courseList = courseList;
        }

        public static class CourseListBean {
            /**
             * id : 1
             * courseName : course0
             * userId : 1
             * type : 1
             * pic : ["http://beautybucket.oss-cn-shenzhen.aliyuncs.com/0a97a05bcbe921ffc412e7f061e16d00.jpg"]
             * popular : 1
             * deployTime : null
             */

            private CourseBean course;
            private int isFollow;

            public CourseBean getCourse() {
                return course;
            }

            public void setCourse(CourseBean course) {
                this.course = course;
            }

            public int getIsFollow() {
                return isFollow;
            }

            public void setIsFollow(int isFollow) {
                this.isFollow = isFollow;
            }

            public static class CourseBean {
                private int id;
                private String courseName;
                private int userId;
                private int type;
                private int popular;
                private Object deployTime;
                private List<String> pic;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCourseName() {
                    return courseName;
                }

                public void setCourseName(String courseName) {
                    this.courseName = courseName;
                }

                public int getUserId() {
                    return userId;
                }

                public void setUserId(int userId) {
                    this.userId = userId;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getPopular() {
                    return popular;
                }

                public void setPopular(int popular) {
                    this.popular = popular;
                }

                public Object getDeployTime() {
                    return deployTime;
                }

                public void setDeployTime(Object deployTime) {
                    this.deployTime = deployTime;
                }

                public List<String> getPic() {
                    return pic;
                }

                public void setPic(List<String> pic) {
                    this.pic = pic;
                }
            }
        }
    }
}
