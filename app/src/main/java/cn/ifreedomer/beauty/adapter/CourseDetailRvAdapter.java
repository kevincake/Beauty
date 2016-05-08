package cn.ifreedomer.beauty.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.CourseItem;
import cn.ifreedomer.beauty.util.IntentUtils;

/**
 * @author:eavawu
 * @date: 5/8/16.
 * @todo:
 */
public class CourseDetailRvAdapter extends CommonAdapter<CourseItem> {
    private Context ctx;
    public CourseDetailRvAdapter(Context context, int layoutId, List<CourseItem> datas) {

        super(context, R.layout.course_detail_rv_item, datas);
        ctx = context;
    }

    @Override
    public void convert(ViewHolder holder, final CourseItem courseItem) {
        holder.getView(R.id.root_view).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.startVideoPlayerActivity((Activity) ctx,courseItem.getUrl());
            }
        });
    }
}
