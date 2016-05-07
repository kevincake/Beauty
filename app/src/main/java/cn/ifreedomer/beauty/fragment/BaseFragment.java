package cn.ifreedomer.beauty.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected View rootView;
    public abstract void initView();
    public abstract void setData();


    public BaseFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = super.onCreateView(inflater, container, savedInstanceState);
        rootView = onCreateRootView(inflater, container, savedInstanceState);
        if (rootView!=null){
            initView();
            setData();
        }

        return rootView;
    }

    protected abstract View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState);

}
