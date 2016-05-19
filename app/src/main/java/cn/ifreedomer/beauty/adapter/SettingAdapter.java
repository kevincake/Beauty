package cn.ifreedomer.beauty.adapter;

import android.content.Context;

import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.SettingItemInfo;

/**
 * @author:eavawu
 * @date: 5/16/16.
 * @todo:
 */
public class SettingAdapter extends CommonAdapter<SettingItemInfo> {
    private static final int VIEW_COUNT = 2;
    private static final int TITLE_TYPE = 0;
    private static final int ITEM_TYPE = 1;

    public SettingAdapter(Context context, int layoutId, List<SettingItemInfo> datas) {
        super(context, R.layout.setting_rv_item, datas);
    }

    @Override
    public void convert(ViewHolder holder, SettingItemInfo settingItemInfo) {
        holder.setText(R.id.content_tv, settingItemInfo.getContent());
        holder.setImageResource(R.id.icon_iv,settingItemInfo.getIcon());
    }

//
//    @Override
//    public int getItemCount() {
//        return VIEW_COUNT;
//    }
//
//    @Override
//    public int getItemViewType(int position) {
//        return mDatas.get(position).getType();
//    }
}
