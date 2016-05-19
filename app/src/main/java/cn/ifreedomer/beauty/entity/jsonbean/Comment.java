package cn.ifreedomer.beauty.entity.jsonbean;

import java.io.Serializable;

/**
 * @author:eavawu
 * @date: 5/14/16.
 * @todo:
 */
public class Comment implements Serializable{
    private Long id;
    private Long socialId;
    private Long fromUserId;
    private String content;
    private Long toUserId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSocialId() {
        return socialId;
    }

    public void setSocialId(Long socialId) {
        this.socialId = socialId;
    }

    public Long getFromUserId() {
        return fromUserId;
    }

    public void setFromUserId(Long fromUserId) {
        this.fromUserId = fromUserId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getToUserId() {
        return toUserId;
    }

    public void setToUserId(Long toUserId) {
        this.toUserId = toUserId;
    }
}
