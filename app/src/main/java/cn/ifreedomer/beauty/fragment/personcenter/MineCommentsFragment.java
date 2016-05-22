package cn.ifreedomer.beauty.fragment.personcenter;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.adapter.SocialRvAdapter;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailList;
import cn.ifreedomer.beauty.fragment.BaseFragment;
import cn.ifreedomer.beauty.listener.OnClickSocialListener;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;

/**
 * @author:eavawu
 * @date: 5/18/16.
 * @todo:
 */
public class MineCommentsFragment extends BaseFragment implements OnClickSocialListener {
    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    private SocialDetailList socialDetailList;
    @Override
    public void initView() {
        ButterKnife.bind(this, rootView);

    }

    @Override
    public void setData() {
        SubscriberOnNextListener<SocialDetailList> subscriberOnNextListener = new SubscriberOnNextListener<SocialDetailList>() {
            @Override
            public void onNext(SocialDetailList socialDetailList) {
                MineCommentsFragment.this.socialDetailList = socialDetailList;
                SocialRvAdapter socialRvAdapter = new SocialRvAdapter(getActivity(), 0, socialDetailList.getSocialDetails(), MineCommentsFragment.this);
                recycleview.setAdapter(socialRvAdapter);
                recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycleview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            }
        };
        HttpMethods.getInstance().getMineSocial(new ProgressSubscriber<SocialDetailList>(subscriberOnNextListener,getActivity()));
    }

    @Override
    protected View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_mine_comments, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClickLikeIB(View view, SocialDetailBean socialDetailBean) {

    }

    @Override
    public void onClickCommentIB(View view, SocialDetailBean socialDetailBean) {

    }

    @Override
    public void onClickLikeCount(View view, SocialDetailBean socialDetailBean) {

    }

    @Override
    public void onClickCommentCount(View view, SocialDetailBean socialDetailBean) {

    }

    @Override
    public void onClickBg(View view, SocialDetailBean socialDetailBean) {

    }

    @Override
    public void onClickMore(View view, SocialDetailBean socialDetailBean) {

    }
}
