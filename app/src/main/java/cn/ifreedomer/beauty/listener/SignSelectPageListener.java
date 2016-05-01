package cn.ifreedomer.beauty.listener;

import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import java.util.List;

import cn.ifreedomer.beauty.R;


/**
 * Created by eavawu on 1/16/16.
 */
public class SignSelectPageListener implements ViewPager.OnPageChangeListener {
    private List<ImageView> dots = null;

    public SignSelectPageListener(List<ImageView> dots) {
        this.dots = dots;
        this.dots.get(0).setImageResource(R.mipmap.dot_green);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        for (int i = 0; i < dots.size(); i++) {
            dots.get(i).setImageResource(R.mipmap.dot);
        }
        this.dots.get(position).setImageResource(R.mipmap.dot_green);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
