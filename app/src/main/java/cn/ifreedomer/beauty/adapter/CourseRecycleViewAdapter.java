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
import cn.ifreedomer.beauty.entity.PopularCourseBean;
import cn.ifreedomer.beauty.listener.TextCheckBoxListener;
import cn.ifreedomer.beauty.notifycation.FollowEvent;
import cn.ifreedomer.beauty.notifycation.NotifycationManager;
import cn.ifreedomer.beauty.util.ImageUtil;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.util.LogUtil;
import cn.ifreedomer.beauty.widget.TextCheckBox;

/**
 * @author:eavawu
 * @date: 5/3/16.
 * @todo:
 */
public class CourseRecycleViewAdapter extends CommonAdapter<PopularCourseBean> implements View.OnClickListener, TextCheckBoxListener {
    private Context ctx;
    private PopularCourseBean curItem;
    public CourseRecycleViewAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
        ctx = context;
    }


    @Override
    public void convert(ViewHolder holder, PopularCourseBean popularCourseBean) {
        LogUtil.error("CourseRecycleViewAdapter", "convert");
        SimpleDraweeView userAvatarIv = holder.getView(R.id.user_circle_iv);
        if (popularCourseBean.getUser() != null) {
            ImageUtil.setFrescoImageView(popularCourseBean.getUser().getAvatar(), userAvatarIv);
            holder.setOnClickListener(R.id.lessonphoto_iv, this);

        }
        SimpleDraweeView courseBgIv = holder.getView(R.id.course_bg_iv);
        ImageUtil.setFrescoImageView(popularCourseBean.getCourse().getPic()[0], courseBgIv);
        holder.setOnClickListener(R.id.lessonphoto_iv, this);
        holder.setText(R.id.course_name_tv, popularCourseBean.getCourse().getCourseName());
        holder.setText(R.id.course_time_tv, popularCourseBean.getCourse().getCourseTime() + "");
        //set follow status
        TextCheckBox followCb = holder.getView(R.id.follow_cb);
        curItem = popularCourseBean;
        followCb.setTextCheckBoxListener(this);
//        textCheckBox.setOn

    }

    @Override
    public void onClick(View view) {
        IntentUtils.startCourseDetailActivity((Activity) ctx);
    }

    @Override
    public void onCheckChangeListener(boolean isCheck) {
        int followStatus = isCheck ? HttpConstants.UNFOLLOWED : HttpConstants.FOLLOWED;
        NotifycationManager.getInstance().post(new FollowEvent(curItem.getUser().getId(),followStatus));
//        HttpMethods.getInstance().postFollowStatus(curItem.getUser().getId(),followStatus);

    }
}
