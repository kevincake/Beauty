package cn.ifreedomer.beauty.adapter;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.io.File;
import java.util.List;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.PhotoPreviewActivity;
import cn.ifreedomer.beauty.util.IntentUtils;

/**
 * @author:eavawu
 * @date: 5/25/16.
 * @todo:
 */
public class PhotoShowRvAdapter extends CommonAdapter<String> {

    public PhotoShowRvAdapter(Context context, int layoutId, List datas) {
        super(context, R.layout.photoshow_rv_item, datas);
    }

    @Override
    public void convert(final ViewHolder holder, final String path) {
        SimpleDraweeView sv = holder.getView(R.id.show_sv);
        sv.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                mDatas.remove(holder.getPosition());
                notifyDataSetChanged();
                return false;
            }
        });
        sv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IntentUtils.startPreviewActivity((Activity) mContext,path, PhotoPreviewActivity.LOCAL);
            }
        });
        sv.setImageURI(Uri.fromFile(new File(path)));// For files on device);
//          ImageUtil.setFrescoImageView(path,sv);
    }


}
