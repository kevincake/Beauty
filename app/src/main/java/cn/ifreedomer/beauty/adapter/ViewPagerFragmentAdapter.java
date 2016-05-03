package cn.ifreedomer.beauty.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:eavawu
 * @date: 5/3/16.
 * @todo:
 */
public class ViewPagerFragmentAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    public ViewPagerFragmentAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public Fragment getItem(int num) {
        return fragments.get(num);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "tab 1";
            case 1:
                return "tab 2";
            case 2:
                return "tab 3";
            default:
                return null;
        }
    }


}
