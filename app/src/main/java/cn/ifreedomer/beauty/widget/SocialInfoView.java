package cn.ifreedomer.beauty.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;
import cn.ifreedomer.beauty.listener.OnClickSocialListener;
import cn.ifreedomer.beauty.util.ImageUtil;
import cn.ifreedomer.beauty.util.IntentUtils;
import me.kaede.tagview.Tag;
import me.kaede.tagview.TagView;

/**
 * @author:eavawu
 * @date: 5/14/16.
 * @todo:
 */
public class SocialInfoView extends RelativeLayout implements View.OnClickListener {
    @Bind(R.id.more_iv)
    ImageView moreIv;

    public OnClickSocialListener getOnClickSocialListener() {
        return onClickSocialListener;
    }

    public void setOnClickSocialListener(OnClickSocialListener onClickSocialListener) {
        this.onClickSocialListener = onClickSocialListener;
    }

    private OnClickSocialListener onClickSocialListener;
    @Bind(R.id.user_circle_iv)
    SimpleDraweeView userCircleIv;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.date_tv)
    TextView dateTv;
    @Bind(R.id.show_iv)
    SimpleDraweeView showIv;
    @Bind(R.id.like_ib)
    ImageButton likeIb;
    @Bind(R.id.comment_ib)
    ImageButton commentIb;
    @Bind(R.id.content_tv)
    TextView contentTv;
    @Bind(R.id.tagview_content)
    TagView tagviewContent;
    @Bind(R.id.likecount_tv)
    TextView likecountTv;
    @Bind(R.id.comment_count_tv)
    TextView commentCountTv;
    @Bind(R.id.root_view)
    LinearLayout rootView;
    private Context context;
    private SocialDetailBean socialDetail;

    public SocialInfoView(Context context) {
        super(context);
        initView(context);
    }


    public SocialInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }


    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.social_info_view, this);
        ButterKnife.bind(this);
    }

    ;

    public void setSocialDetail(SocialDetailBean socialDetail) {
        this.socialDetail = socialDetail;
        nameTv.setText(socialDetail.getUser().getName());
        ImageUtil.setFrescoImageView(socialDetail.getUser().getAvatar(), userCircleIv);
        ImageUtil.setFrescoImageView(socialDetail.getSocialEntity().getPic()[0], showIv);
        int likeCount = socialDetail.getLikeEntities() == null || socialDetail.getLikeEntities().isEmpty() ? 0 : socialDetail.getLikeEntities().size();
        int followCount = socialDetail.getCommentsEntities() == null || socialDetail.getCommentsEntities().isEmpty() ? 0 : socialDetail.getCommentsEntities().size();
        likecountTv.setText(String.format(context.getString(R.string.like_wrap), likeCount));
        commentCountTv.setText(String.format(context.getString(R.string.followcount_wrap), followCount));
        TagView tagView = tagviewContent;
        tagView.removeAllTags();
        tagView.addTag(new Tag("风景", R.color.WHITE));
        tagView.addTag(new Tag("旅游", R.color.WHITE));


//        int status = likeIb.isSelected()? HttpConstants.LIKE_STATUS:HttpConstants.UNLIKE_STATUS;
//        SendLikeEvent sendLikeEvent = new SendLikeEvent(socialDetail);
//        sendLikeEvent.setStatus(status);
//        NotifycationManager.getInstance().post(sendLikeEvent);

//        holder.setText(R.id.likecount_tv, );
//        holder.setText(R.id.followcount_tv,);
    }

    @Override
    @OnClick({R.id.likecount_tv, R.id.comment_count_tv, R.id.like_ib, R.id.comment_ib,R.id.more_iv,R.id.show_iv})
    public void onClick(View view) {
        if (onClickSocialListener == null) {
            return;
        }
        switch (view.getId()) {
            case R.id.more_iv:
                onClickSocialListener.onClickMore(view,socialDetail);
                break;
            case R.id.likecount_tv:
                onClickSocialListener.onClickLikeCount(view, socialDetail);
                break;
            case R.id.comment_count_tv:
                onClickSocialListener.onClickCommentCount(view, socialDetail);
                break;
            case R.id.like_ib:
                if (likeIb.isSelected()) {
                    likeIb.setSelected(false);
                } else {
                    likeIb.setSelected(true);
                }
                onClickSocialListener.onClickLikeIB(view, socialDetail);
//                int status = likeIb.isSelected() ? HttpConstants.LIKE_STATUS : HttpConstants.UNLIKE_STATUS;
//                SendLikeEvent sendLikeEvent = new SendLikeEvent(socialDetail);
//                sendLikeEvent.setStatus(status);
//                NotifycationManager.getInstance().post(sendLikeEvent);
                break;
            case R.id.comment_ib:
                onClickSocialListener.onClickCommentIB(view, socialDetail);

                break;
            case R.id.show_iv:
                IntentUtils.startPreviewActivity((Activity) context,socialDetail.getSocialEntity().getPic()[0]);
//                onClickSocialListener.onClickBg(view,socialDetail);
                break;
        }

    }
}
