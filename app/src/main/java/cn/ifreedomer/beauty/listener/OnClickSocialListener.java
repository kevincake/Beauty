package cn.ifreedomer.beauty.listener;

import android.view.View;

import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;

/**
 * @author:eavawu
 * @date: 5/15/16.
 * @todo:
 */
public interface OnClickSocialListener {
    void onClickLikeIB(View view,SocialDetailBean socialDetailBean);
    void onClickCommentIB(View view, SocialDetailBean socialDetailBean);
    void onClickLikeCount(View view,SocialDetailBean socialDetailBean);
    void onClickCommentCount(View view,SocialDetailBean socialDetailBean);
    void onClickBg(View view,SocialDetailBean socialDetailBean);
    void onClickMore(View view,SocialDetailBean socialDetailBean);
}
