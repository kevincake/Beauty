package cn.ifreedomer.beauty.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.entity.jsonbean.PopularCourseBean;
import cn.ifreedomer.beauty.listener.TextCheckBoxListener;
import cn.ifreedomer.beauty.notifycation.FollowEvent;
import cn.ifreedomer.beauty.notifycation.NotifycationManager;
import cn.ifreedomer.beauty.util.ImageUtil;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.util.LogUtil;
import cn.ifreedomer.beauty.widget.CourseInfoView;
import cn.ifreedomer.beauty.widget.TextCheckBox;

/**
 * @author:eavawu
 * @date: 5/3/16.
 * @todo:
 */
public class CourseRecycleViewAdapter extends CommonAdapter<PopularCourseBean> {
    private Context ctx;
    private PopularCourseBean curItem;

    public CourseRecycleViewAdapter(Context context, int layoutId, List datas) {
        super(context, R.layout.coursefragment_rv_item, datas);
        ctx = context;
    }



    @Override
    public void convert(ViewHolder holder, final PopularCourseBean popularCourseBean) {
        LogUtil.error("CourseRecycleViewAdapter", "convert");
        SimpleDraweeView userAvatarIv = holder.getView(R.id.user_circle_iv);
        if (popularCourseBean.getUser() != null) {
            ImageUtil.setFrescoImageView(popularCourseBean.getUser().getAvatar(), userAvatarIv);


        }
        holder.setText(R.id.name_tv,popularCourseBean.getUser().getName());
        CourseInfoView courseInfoView = holder.getView(R.id.course_info_view);
        courseInfoView.setCourse(popularCourseBean.getCourse());
        courseInfoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.startCourseDetailActivity((Activity) ctx,popularCourseBean);
            }
        });

        TextCheckBox followCb = holder.getView(R.id.follow_cb);
        followCb.setChecked(popularCourseBean.getIsFollow()==HttpConstants.FOLLOWED);

        followCb.setTextCheckBoxListener(new TextCheckBoxListener() {
            @Override
            public void onCheckChangeListener(boolean isCheck) {
                LogUtil.error("setTextCheckBoxListener","TIME");
                int followStatus = isCheck ? HttpConstants.FOLLOWED : HttpConstants.UNFOLLOWED;
                NotifycationManager.getInstance().post(new FollowEvent(popularCourseBean.getUser().getId(), followStatus));
            }
        });
//        textCheckBox.setOn

    }


}
