package cn.ifreedomer.beauty.adapter;

import android.content.Context;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;
import cn.ifreedomer.beauty.listener.OnClickSocialListener;
import cn.ifreedomer.beauty.widget.SocialInfoView;

/**
 * @author:eavawu
 * @date: 5/9/16.
 * @todo:
 */
public class SocialRvAdapter extends CommonAdapter<SocialDetailBean> {
    private Context context;
    private OnClickSocialListener onClickSocialListener;
    public SocialRvAdapter(Context context, int layoutId, List<SocialDetailBean> datas, OnClickSocialListener onClickSocialListener) {
        super(context, R.layout.social_rv_item, datas);
        this.context = context;
        this.onClickSocialListener = onClickSocialListener;
    }

    @Override
    public void convert(ViewHolder holder, SocialDetailBean socialItem) {
        SocialInfoView socialInfoView = holder.getView(R.id.social_info_view);
        socialInfoView.setSocialDetail(socialItem);
        socialInfoView.setOnClickSocialListener(onClickSocialListener);
//        holder.setOnClickListener(R.id.root_view, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        SimpleDraweeView avatarView = holder.getView(R.id.user_circle_iv);
//        ImageUtil.setFrescoImageView(socialItem.getUser().getAvatar(), avatarView);
//
//
//
//
//
//        holder.setText(R.id.name_tv, socialItem.getUser().getName());
//        holder.setText(R.id.content_tv, socialItem.getSocialEntity().getContent());
//        int likeCount = socialItem.getLikeEntities() == null || socialItem.getLikeEntities().isEmpty() ? 0 : socialItem.getLikeEntities().size();
//        int followCount = socialItem.getCommentsEntities() == null || socialItem.getCommentsEntities().isEmpty() ? 0 : socialItem.getCommentsEntities().size();
//        holder.setText(R.id.likecount_tv, String.format(context.getString(R.string.like_wrap), likeCount));
//        holder.setText(R.id.followcount_tv, String.format(context.getString(R.string.followcount_wrap), followCount));
//        SimpleDraweeView contentImg = holder.getView(R.id.show_iv);
//        ImageUtil.setFrescoImageView(socialItem.getSocialEntity().getPic()[0], contentImg);
//
//        holder.setOnClickListener(R.id.like_ib, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
//
//        holder.setOnClickListener(R.id.comment_ib, new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }
}
