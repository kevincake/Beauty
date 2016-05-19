package cn.ifreedomer.beauty.notifycation;

import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;

/**
 * @author:eavawu
 * @date: 5/15/16.
 * @todo:
 */
public class SendLikeEvent {
    private SocialDetailBean socialDetailBean;
    private int status;
    public SocialDetailBean getSocialDetailBean() {
        return socialDetailBean;
    }

    public void setSocialDetailBean(SocialDetailBean socialDetailBean) {
        this.socialDetailBean = socialDetailBean;
    }

    public int getStatus() {
        return status;
    }

    public SendLikeEvent(SocialDetailBean socialDetail) {
        socialDetailBean = socialDetail;

    }

    public void setStatus(int status) {
        this.status = status;
    }
}
