package cn.ifreedomer.beauty.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;

/**
 * @author:eavawu
 * @date: 5/22/16.
 * @todo:
 */
public class IconTableView extends RelativeLayout {
    @Bind(R.id.icon_iv)
    ImageView iconIv;

    public IconTableView(Context context) {
        super(context);
        initView(context);
    }

    public IconTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);

    }

    public void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.main_tab_customview, this);
        ButterKnife.bind(this);
    }

    public void setIcon(int resId){
        iconIv.setImageResource(resId);
    }
}
