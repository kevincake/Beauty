package cn.ifreedomer.beauty.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.orangegangsters.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.PhotoPreviewActivity;
import cn.ifreedomer.beauty.adapter.SocialRvAdapter;
import cn.ifreedomer.beauty.constants.HttpConstants;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.entity.jsonbean.Like;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailList;
import cn.ifreedomer.beauty.listener.OnClickSocialListener;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.notifycation.NotifycationManager;
import cn.ifreedomer.beauty.notifycation.SendLikeEvent;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.IntentUtils;
import cn.ifreedomer.beauty.util.LogUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialFragment extends BaseFragment implements OnClickSocialListener, SwipyRefreshLayout.OnRefreshListener {


    @Bind(R.id.social_rv)
    RecyclerView socialRv;
    @Bind(R.id.swipe_layout)
    SwipyRefreshLayout swipeLayout;
    private SubscriberOnNextListener<Like> postLikeSub;
    private SocialDetailList socialDetailList;
    private SendLikeEvent sendLikeEvent;
    private SocialRvAdapter socialRvAdapter;
    public static final int MINE_TYPE = 1;
    public static final int COMMON_TYPE = 2;
    private static int showType = COMMON_TYPE;
    private int curPageIndex = 1;
    private List<SocialDetailBean> socialDetailBeanList = new ArrayList<>();
    private SwipyRefreshLayoutDirection currentDirection = SwipyRefreshLayoutDirection.BOTTOM;
    private SubscriberOnNextListener<SocialDetailList> getDetailsSub;


    @Override
    public void initView() {
        ButterKnife.bind(this, rootView);
        NotifycationManager.getInstance().register(this);
//        swipeLayout.setColorSchemeColors(android.R.color.white,
//                android.R.color.holo_green_light,
//                android.R.color.holo_orange_light, android.R.color.holo_red_light);
        swipeLayout.setOnRefreshListener(this);
        ;
        socialRvAdapter = new SocialRvAdapter(getActivity(), 0, socialDetailBeanList, SocialFragment.this);
        socialRv.setAdapter(socialRvAdapter);
        socialRv.setLayoutManager(new LinearLayoutManager(getActivity()));
        socialRv.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));



        getDetailsSub = new SubscriberOnNextListener<SocialDetailList>() {
            @Override
            public void onNext(SocialDetailList socialDetailList) {
                if (socialDetailList == null || socialDetailList.getSocialDetails() == null) return;
                LogUtil.info("getDetailsSub", socialDetailList.toString());
                if (currentDirection == SwipyRefreshLayoutDirection.BOTTOM) {
                    curPageIndex = curPageIndex+1;
                    socialDetailBeanList.addAll(socialDetailList.getSocialDetails());
                } else {

                    socialDetailBeanList.clear();
                    socialDetailBeanList.addAll(socialDetailList.getSocialDetails());
                }
                swipeLayout.setRefreshing(false);
                socialRvAdapter.notifyDataSetChanged();
//                SocialFragment.this.socialDetailList = socialDetailList;

            }
        };

        showType = getArguments().getInt(IntentConstants.SOCIAL_SHOWTYPE);
        if (showType == MINE_TYPE) {
            swipeLayout.setEnabled(false);
            HttpMethods.getInstance().getMineSocial(new ProgressSubscriber<SocialDetailList>(getDetailsSub, getActivity()));
        } else {
            HttpMethods.getInstance().getSocialDetails(new ProgressSubscriber<SocialDetailList>(getDetailsSub, getActivity()));
        }


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
        PopupMenu popupMenu = new PopupMenu(getActivity(), anchorView);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.person_more_menu, popupMenu.getMenu());
        popupMenu.show();
//        View view = View.inflate(getActivity(), R.layout.social_menu_more, null);
//        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.findViewById(R.id.root_view).getLayoutParams();
////        final PopupWindow popupWindow = new PopupWindow(view, DensityUtil.dip2px(getActivity(),getResources().getDimension(R.dimen.dimen_px250)),
////                DensityUtil.dip2px(getActivity(),getResources().getDimension(R.dimen.dimen_px200)),true);
//
//
//        final PopupWindow popupWindow = new PopupWindow(view, layoutParams.width,
//                layoutParams.height, true);
//        ArrayList<String> items = new ArrayList<>();
//        popupWindow.setTouchable(true);
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        popupWindow.showAsDropDown(anchorView, DensityUtil.dip2px(getActivity(), getResources().getDimension(R.dimen.dimen_pxf20)), DensityUtil.dip2px(getActivity(), getResources().getDimension(R.dimen.dimen_pxf40)));

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
        if (socialDetailBean.getSocialEntity().getPic().size() > 0) {
            IntentUtils.startPreviewActivity(getActivity(), socialDetailBean.getSocialEntity().getPic().get(0), PhotoPreviewActivity.NETWORK);
        }

    }

    @Override
    public void onClickMore(View view, SocialDetailBean socialDetailBean) {
        showMenuPop(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void onRefresh(SwipyRefreshLayoutDirection direction) {
        currentDirection = direction;
        if (direction == SwipyRefreshLayoutDirection.BOTTOM) {
//            curPageIndex = curPageIndex + 1;
            HttpMethods.getInstance().getSocialDetails(new ProgressSubscriber<SocialDetailList>(getDetailsSub, getActivity(), false));
        } else {
            curPageIndex = 1;
            HttpMethods.getInstance().getSocialDetails(new ProgressSubscriber<SocialDetailList>(getDetailsSub, getActivity(), false));
        }


    }
}
