package cn.ifreedomer.beauty.entity.jsonbean;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.ifreedomer.beauty.entity.User;

/**
 * @atuhor:eavawu
 * @date:5/14/16
 * @todo:
 */
public class SocialDetailBean implements Serializable {
    private Social socialEntity;
    private List<Like> likeEntities = new ArrayList<>();
    private List<Comment> commentsEntities = new ArrayList<>();
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Social getSocialEntity() {
        return socialEntity;
    }

    public void setSocialEntity(Social socialEntity) {
        this.socialEntity = socialEntity;
    }

    public List<Like> getLikeEntities() {
        if (likeEntities==null){
            likeEntities = new ArrayList<>();
        }
        return likeEntities;
    }

    public void setLikeEntities(List<Like> likeEntities) {
        this.likeEntities = likeEntities;
    }

    public List<Comment> getCommentsEntities() {
        return commentsEntities;
    }

    public void setCommentsEntities(List<Comment> commentsEntities) {
        this.commentsEntities = commentsEntities;
    }
}
