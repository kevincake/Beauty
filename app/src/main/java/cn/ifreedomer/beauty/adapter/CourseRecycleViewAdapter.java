package cn.ifreedomer.beauty.adapter;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.PopularCourseBean;
import cn.ifreedomer.beauty.util.LogUtil;

/**
 * @author:eavawu
 * @date: 5/3/16.
 * @todo:
 */
public class CourseRecycleViewAdapter extends CommonAdapter<PopularCourseBean> {


    public CourseRecycleViewAdapter(Context context, int layoutId, List datas) {
        super(context, layoutId, datas);
    }


    @Override
    public void convert(ViewHolder holder, PopularCourseBean popularCourseBean) {
        LogUtil.error("CourseRecycleViewAdapter", "convert");
        SimpleDraweeView view = holder.getView(R.id.user_circle_iv);
        if (popularCourseBean.getUser()!=null){
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(popularCourseBean.getUser().getAvatar())
                    //构建
                    .build();
            view.setController(controller);
        }

    }
}
