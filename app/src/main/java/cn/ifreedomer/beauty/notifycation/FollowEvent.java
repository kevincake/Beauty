package cn.ifreedomer.beauty.notifycation;

/**
 * @author:eavawu
 * @date: 5/7/16.
 * @todo:
 */
public class FollowEvent {
    private long userId;
    private int followStatus;

    public FollowEvent(long userId, int followStatus) {
        this.userId = userId;
        this.followStatus = followStatus;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public int getFollowStatus() {
        return followStatus;
    }

    public void setFollowStatus(int followStatus) {
        this.followStatus = followStatus;
    }

    @Override
    public String toString() {
        return "FollowEvent{" +
                "userId=" + userId +
                ", followStatus=" + followStatus +
                '}';
    }
}
