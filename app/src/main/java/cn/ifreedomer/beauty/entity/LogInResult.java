package cn.ifreedomer.beauty.entity;

import cn.ifreedomer.beauty.entity.base.TokenBase;

/**
 * @author:eavawu
 * @date: 5/3/16.
 * @todo:
 */
public class LogInResult extends TokenBase {
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
