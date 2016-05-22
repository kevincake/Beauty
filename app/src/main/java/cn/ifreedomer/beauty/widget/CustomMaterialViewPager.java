package cn.ifreedomer.beauty.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.github.florent37.materialviewpager.MaterialViewPager;

/**
 * @author:eavawu
 * @date: 5/22/16.
 * @todo:
 */
public class CustomMaterialViewPager extends MaterialViewPager {
    public CustomMaterialViewPager(Context context) {
        super(context);
    }

    public CustomMaterialViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setHeader(View view){

    }
}
