package cn.ifreedomer.beauty.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:eavawu
 * @date: 5/3/16.
 * @todo:
 */
public class ViewPagerFragmentAdapter extends MainFragmentPagerAdapter {
    private List<Fragment> fragments = new ArrayList<>();
    private FragmentManager mFragmentManager;
    private FragmentTransaction mCurTransaction;
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
//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//        if (mCurTransaction == null) {
//            mCurTransaction = mFragmentManager.beginTransaction();
//        }
//
//        final long itemId = getItemId(position);
//
//        // Do we already have this fragment?
//        String name = makeFragmentName(container.getId(), itemId);
//        Fragment fragment = mFragmentManager.findFragmentByTag(name);
////        if (fragment != null) {
////            if (DEBUG) Log.v(TAG, "Attaching item #" + itemId + ": f=" + fragment);
////            mCurTransaction.attach(fragment);
////        } else {
////            fragment = getItem(position);
////            if (DEBUG) Log.v(TAG, "Adding item #" + itemId + ": f=" + fragment);
////            mCurTransaction.add(container.getId(), fragment,
////                    makeFragmentName(container.getId(), itemId));
////        }
////        if (fragment != mCurrentPrimaryItem) {
////            fragment.setMenuVisibility(false);
////            fragment.setUserVisibleHint(false);
////        }
//
//        return fragment;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        if (mCurTransaction == null) {
//            mCurTransaction = mFragmentManager.beginTransaction();
//        }
////        if (DEBUG) Log.v(TAG, "Detaching item #" + getItemId(position) + ": f=" + object
////                + " v=" + ((Fragment)object).getView());
//        mCurTransaction.detach((Fragment)object);
//    }
//
//    private static String makeFragmentName(int viewId, long id) {
//        return "android:switcher:" + viewId + ":" + id;
//    }

}
