package cn.ifreedomer.beauty.entity.jsonbean;

import java.util.List;

import cn.ifreedomer.beauty.entity.User;

/**
 * @author:eavawu
 * @date: 5/17/16.
 * @todo:
 */
public class FollowListResult {
    private List<User> followEntities;
    private List<User> beFollowEntities;

    public List<User> getBeFollowEntities() {
        return beFollowEntities;
    }

    public void setBeFollowEntities(List<User> beFollowEntities) {
        this.beFollowEntities = beFollowEntities;
    }

    public List<User> getFollowEntities() {
        return followEntities;
    }

    public void setFollowEntities(List<User> followEntities) {
        this.followEntities = followEntities;
    }
}
