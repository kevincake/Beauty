package cn.ifreedomer.beauty.adapter;

import android.content.Context;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.FollowEntity;

/**
 * @author:eavawu
 * @date: 5/16/16.
 * @todo:
 */
public class FollowAdapter extends CommonAdapter<FollowEntity> {


    public FollowAdapter(Context context, int layoutId, List<FollowEntity> datas) {
        super(context, R.layout.follow_rv_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, FollowEntity followEntity) {

    }
}
