package cn.ifreedomer.beauty.entity;

/**
 * @author:eavawu
 * @date: 5/16/16.
 * @todo:
 */
public class FollowEntity {
    private Long id;

    public FollowEntity() {
    }

    ;

    public FollowEntity(Long followerId, Long beFollowerId) {
        this.followerId = followerId;
        this.beFollowerId = beFollowerId;
    }

    public Long getId() {
        return id;
    }

    private Long followerId; //我的follower
    private Long beFollowerId;//被我follower
}
