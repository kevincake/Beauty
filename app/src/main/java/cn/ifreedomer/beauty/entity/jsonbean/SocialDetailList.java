package cn.ifreedomer.beauty.entity.jsonbean;

import java.util.List;

/**
 * @author:eavawu
 * @date: 5/14/16.
 * @todo:
 */
public class SocialDetailList {
    private List<SocialDetailBean> socialDetails;

    public List<SocialDetailBean> getSocialDetails() {
        return socialDetails;
    }

    public void setSocialDetails(List<SocialDetailBean> socialDetails) {
        this.socialDetails = socialDetails;
    }

    @Override
    public String toString() {
        return "SocialDetailList{" +
                "socialDetails=" + socialDetails +
                '}';
    }
}
