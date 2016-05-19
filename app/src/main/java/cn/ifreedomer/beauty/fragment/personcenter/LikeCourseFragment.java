package cn.ifreedomer.beauty.fragment.personcenter;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.fragment.BaseFragment;

/**
 * @author:eavawu
 * @date: 5/18/16.
 * @todo:
 */
public class LikeCourseFragment extends BaseFragment {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;

    @Override
    public void initView() {
        ButterKnife.bind(this, rootView);
    }

    @Override
    public void setData() {

    }

    @Override
    protected View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_like_course, container, false);
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
