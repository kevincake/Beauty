package cn.ifreedomer.beauty.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.adapter.ArticleRecycleViewAdapter;
import cn.ifreedomer.beauty.adapter.CourseRecycleViewAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleFragment extends BaseFragment {


    @Bind(R.id.recycleview)
    RecyclerView recycleview;

    @Override
    public void initView() {
        ButterKnife.bind(this, rootView);
        recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
        CourseRecycleViewAdapter adapter = new ArticleRecycleViewAdapter(getActivity(),R.layout.fragment_article_rv_item, courseList);
        recycleview.setAdapter(adapter);
    }

    @Override
    public void setData() {

    }

    @Override
    protected View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
