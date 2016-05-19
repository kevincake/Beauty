package cn.ifreedomer.beauty.fragment;


import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.adapter.SocialRvAdapter;
import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.entity.SocialItem;
import cn.ifreedomer.beauty.entity.jsonbean.Like;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailList;
import cn.ifreedomer.beauty.listener.OnClickSocialListener;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.notifycation.NotifycationManager;
import cn.ifreedomer.beauty.notifycation.SendLikeEvent;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.DensityUtil;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialFragment extends BaseFragment implements OnClickSocialListener {


    @Bind(R.id.social_rv)
    RecyclerView socialRv;
    private SubscriberOnNextListener<Like> postLikeSub;
    private SocialDetailList socialDetailList;
    private SendLikeEvent sendLikeEvent;
    private SocialRvAdapter socialRvAdapter;

    @Override
    public void initView() {
        ButterKnife.bind(this, rootView);
        NotifycationManager.getInstance().register(this);
        ArrayList<SocialItem> items = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            items.add(new SocialItem());
        }
        ;

        SubscriberOnNextListener<SocialDetailList> getDetailsSub = new SubscriberOnNextListener<SocialDetailList>() {
            @Override
            public void onNext(SocialDetailList socialDetailList) {
                if (socialDetailList == null || socialDetailList.getSocialDetails() == null) return;
                LogUtil.info("getDetailsSub", socialDetailList.toString());
                SocialFragment.this.socialDetailList = socialDetailList;
                socialRvAdapter = new SocialRvAdapter(getActivity(), 0, socialDetailList.getSocialDetails(), SocialFragment.this);
                socialRv.setAdapter(socialRvAdapter);
                socialRv.setLayoutManager(new LinearLayoutManager(getActivity()));
                socialRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
            }
        };

        HttpMethods.getInstance().getSocialDetails(new ProgressSubscriber<SocialDetailList>(getDetailsSub, getActivity()));

        //like
        postLikeSub = new SubscriberOnNextListener<Like>() {

            @Override
            public void onNext(Like like) {

                if (sendLikeEvent.getStatus() == HttpConstants.LIKE_STATUS) {
                    sendLikeEvent.getSocialDetailBean().getLikeEntities().add(like);
                } else {
                    for (Like likeTemp : sendLikeEvent.getSocialDetailBean().getLikeEntities()) {
                        if (like.getId() == likeTemp.getId()) {
                            sendLikeEvent.getSocialDetailBean().getLikeEntities().remove(likeTemp);
                            break;
                        }

                    }
                }
                socialRvAdapter.notifyDataSetChanged();
            }

        };


    }

    private void showMenuPop(View anchorView) {
        View view = View.inflate(getActivity(), R.layout.social_menu_more, null);
        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.findViewById(R.id.root_view).getLayoutParams();
//        final PopupWindow popupWindow = new PopupWindow(view, DensityUtil.dip2px(getActivity(),getResources().getDimension(R.dimen.dimen_px250)),
//                DensityUtil.dip2px(getActivity(),getResources().getDimension(R.dimen.dimen_px200)),true);


        final PopupWindow popupWindow = new PopupWindow(view, layoutParams.width,
                layoutParams.height, true);
        ArrayList<String> items = new ArrayList<>();
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(anchorView, DensityUtil.dip2px(getActivity(), getResources().getDimension(R.dimen.dimen_pxf20)), DensityUtil.dip2px(getActivity(), getResources().getDimension(R.dimen.dimen_pxf40)));

//        popupWindow.setWidth(500);

    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void followEventReceive(SendLikeEvent sendLikeEvent) {
        sendLikeEvent.getSocialDetailBean();
        this.sendLikeEvent = sendLikeEvent;
        LogUtil.error("followEventReceive", sendLikeEvent.toString());
        HttpMethods.getInstance().postLikeStatus(new ProgressSubscriber(postLikeSub, getActivity()), sendLikeEvent.getSocialDetailBean().getSocialEntity().getId(), sendLikeEvent.getStatus());
    }

    @Override
    public void setData() {

    }

    public SocialFragment() {
        // Required empty public constructor
    }

    @Override
    protected View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_social, container, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        NotifycationManager.getInstance().unregister(this);
    }

    @Override
    public void onClickLikeIB(View view, SocialDetailBean socialDetailBean) {
        int status = view.isSelected() ? HttpConstants.LIKE_STATUS : HttpConstants.UNLIKE_STATUS;
        SendLikeEvent sendLikeEvent = new SendLikeEvent(socialDetailBean);
        sendLikeEvent.setStatus(status);
        NotifycationManager.getInstance().post(sendLikeEvent);
    }

    @Override
    public void onClickCommentIB(View view, SocialDetailBean socialDetailBean) {
        IntentUtils.startCommentActivity(getActivity(), socialDetailBean);
    }

    @Override
    public void onClickLikeCount(View view, SocialDetailBean socialDetailBean) {

    }

    @Override
    public void onClickCommentCount(View view, SocialDetailBean socialDetailBean) {
        IntentUtils.startCommentActivity(getActivity(), socialDetailBean);

    }

    @Override
    public void onClickBg(View view, SocialDetailBean socialDetailBean) {

    }

    @Override
    public void onClickMore(View view, SocialDetailBean socialDetailBean) {
        showMenuPop(view);
    }
}
