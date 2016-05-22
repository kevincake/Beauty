package cn.ifreedomer.beauty.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.adapter.ArticleRecycleViewAdapter;
import cn.ifreedomer.beauty.decorate.VerticalSpaceItemDecoration;
import cn.ifreedomer.beauty.entity.jsonbean.ArticleListResult;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.DensityUtil;

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
//        ArticleListResult articleListResult = new ArticleListResult();





    }

    @Override
    public void setData() {
        SubscriberOnNextListener<ArticleListResult> articleSubcribe = new SubscriberOnNextListener<ArticleListResult>() {
            @Override
            public void onNext(ArticleListResult articleListResult) {
                recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycleview.addItemDecoration(new VerticalSpaceItemDecoration(DensityUtil.dip2px(getActivity(),getResources().getDimension(R.dimen.dimen_dp10))));
                ArticleRecycleViewAdapter adapter = new ArticleRecycleViewAdapter(getActivity(),R.layout.fragment_article_rv_item, articleListResult.getArticleList());
                recycleview.setAdapter(adapter);
            }
        };
        HttpMethods.getInstance().getPopularArticleList(new ProgressSubscriber<ArticleListResult>(articleSubcribe,getActivity()),1);
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
