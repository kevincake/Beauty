package cn.ifreedomer.beauty.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.Goods;
import cn.ifreedomer.beauty.util.DensityUtil;

/**
 * @author:eavawu
 * @date: 5/28/16.
 * @todo:
 */
public class GoodsListRvAdapter extends CommonAdapter<Goods> {
    private List<Integer> mHeights;

    public GoodsListRvAdapter(Context context, int layoutId, List<Goods> datas) {
        super(context, R.layout.goodslist_rv_item, datas);
        mHeights = new ArrayList<Integer>();
        for (int i = 0; i < datas.size(); i++)
        {
            if (i<2) {
                mHeights.add(DensityUtil.dip2px(context,context.getResources().getDimension(R.dimen.dimen_dp50)));
            }else{
//                mHeights.add( (int) (150 + Math.random() * 300));
                int randomHeight = (int) (DensityUtil.dip2px(context, context.getResources().getDimension(R.dimen.dimen_dp50)) + DensityUtil.dip2px(context, context.getResources().getDimension(R.dimen.dimen_dp30)) * Math.random());
                mHeights.add(randomHeight);
            }
        }
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        super.onBindViewHolder(holder, position);
        SimpleDraweeView view = holder.getView(R.id.goodsimg_iv);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (mHeights.get(position));
    }

    @Override
    public void convert(ViewHolder holder, Goods goods) {

    }
}
