package cn.ifreedomer.beauty.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ifreedomer.beauty.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MomentsFragment extends BaseFragment {


    @Override
    public void initView() {

    }

    @Override
    public void setData() {

    }

    public MomentsFragment() {
        // Required empty public constructor
    }




    @Override
    protected View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_moments, container, false);
    }


}
