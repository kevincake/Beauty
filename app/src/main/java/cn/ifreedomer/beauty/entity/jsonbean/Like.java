package cn.ifreedomer.beauty.entity.jsonbean;

import java.io.Serializable;

/**
 * @author:eavawu
 * @date: 5/14/16.
 * @todo:
 */
public class Like implements Serializable{
    private Long id;
    private Long socialId;
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
