package cn.ifreedomer.beauty.entity.jsonbean;

import java.io.Serializable;

/**
 * @author:eavawu
 * @date: 5/14/16.
 * @todo:
 */
public class Social implements Serializable{
    private Long userId;

    private Long id;
    private String pic[];
    private String content;
    private Long deployTime;
    private int type; //类型
    private String url;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String[] getPic() {
        return pic;
    }

    public void setPic(String[] pic) {
        this.pic = pic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getDeployTime() {
        return deployTime;
    }

    public void setDeployTime(Long deployTime) {
        this.deployTime = deployTime;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
