package cn.ifreedomer.beauty.fragment.personcenter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.adapter.ViewPagerFragmentAdapter;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.entity.User;
import cn.ifreedomer.beauty.entity.jsonbean.FollowListResult;
import cn.ifreedomer.beauty.fragment.BaseFragment;
import cn.ifreedomer.beauty.fragment.SocialFragment;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonCenterFragment extends BaseFragment {


    @Bind(R.id.viewpager_person)
    MaterialViewPager viewpager;
//    @Bind(R.id.scrollView)
//    ScrollView scrollView;
    private List<User> followEntityList;
    private List<User> beFollowEntityList;
    private List<Fragment> personfragments = new ArrayList<>();
    private int[] titles = new int[]{R.string.like_course, R.string.comments};

    @Override
    public void initView() {
        ButterKnife.bind(this, rootView);



        initFragments();
        initPageAndTabHost();
    }

    @Override
    public void setData() {
        SubscriberOnNextListener<FollowListResult> subFollowList = new SubscriberOnNextListener<FollowListResult>() {
            @Override
            public void onNext(FollowListResult followList) {
//                followEntityList = followList.getFollowEntities();
//                beFollowEntityList = followList.getBeFollowEntities();
//                int fansCount = followEntityList == null || followEntityList.isEmpty() ? 0 : followEntityList.size();
//                int mineFollowCount = beFollowEntityList == null || beFollowEntityList.isEmpty() ? 0 : beFollowEntityList.size();
//                fansTv.setText(fansCount + "." + getString(R.string.follow));
//                mineFollowsTv.setText(getString(R.string.follow) + "." + mineFollowCount + "");
            }
        };
        HttpMethods.getInstance().getFollowList(new ProgressSubscriber<FollowListResult>(subFollowList, getActivity()));


    }


//    private void showMenuPop(View anchorView) {
//        PopupMenu popupMenu = new PopupMenu(getActivity(), personMoreIv);
//        MenuInflater menuInflater = popupMenu.getMenuInflater();
//        menuInflater.inflate(R.menu.person_more_menu, popupMenu.getMenu());
//        popupMenu.show();
//
////        View view = View.inflate(getActivity(), R.layout.person_menu_more, null);
////        LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) view.findViewById(R.id.root_view).getLayoutParams();
//////        final PopupWindow popupWindow = new PopupWindow(view, DensityUtil.dip2px(getActivity(),getResources().getDimension(R.dimen.dimen_px250)),
//////                DensityUtil.dip2px(getActivity(),getResources().getDimension(R.dimen.dimen_px200)),true);
////
////
////        final PopupWindow popupWindow = new PopupWindow(view, layoutParams.width,
////                layoutParams.height, true);
////        ArrayList<String> items = new ArrayList<>();
////        popupWindow.setTouchable(true);
////        popupWindow.setOutsideTouchable(true);
////        popupWindow.setBackgroundDrawable(new BitmapDrawable());
////        popupWindow.showAsDropDown(anchorView, DensityUtil.dip2px(getActivity(), getResources().getDimension(R.dimen.dimen_pxf20)), DensityUtil.dip2px(getActivity(), getResources().getDimension(R.dimen.dimen_pxf40)));
//
////        popupWindow.setWidth(500);
//
//    }

    public PersonCenterFragment() {
        // Required empty public constructor
    }


    private void initFragments() {
        personfragments.add(new LikeCourseFragment());
        Bundle bundle = new Bundle();
        bundle.putInt(IntentConstants.SOCIAL_SHOWTYPE, SocialFragment.MINE_TYPE);
        SocialFragment socialFragment = new SocialFragment();
        socialFragment.setArguments(bundle);
        personfragments.add(socialFragment);


    }


    private void initPageAndTabHost() {
        // init view pager
        ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), personfragments);
        viewpager.getViewPager().setAdapter(pagerAdapter);
//        ViewGroup headerBackgroundContainer = viewpager.getHeaderBackgroundContainer();
//        headerBackgroundContainer.addView(LayoutInflater.from(getContext()).inflate(R.layout.person_headerview, headerBackgroundContainer, false));
//        viewpager.(pagerAdapter);
//        tabHost.setupWithViewPager(viewpager);
//        tabHost.getTabAt(0).setText(getString(R.string.like_course));
//        tabHost.getTabAt(1).setText(getString(R.string.comments));
//        viewpager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
//            @Override
//            public void onPageSelected(int position) {
//                // when user do a swipe the selected tab change
//                if (position < tabHost.getTabCount()) {
//                    tabHost.setSelectedNavigationItem(position);
//                }
//
//            }
//        });
//        // insert all tabs from pagerAdapter data
//        for (int i = 0; i < personfragments.size(); i++) {
//            if (i >= titles.length) return;
//            tabHost.addTab(
//                    tabHost.newTab()
//                            .setText(getString(titles[i]))
//                            .setTabListener(this)
//            );
//        }
    }

    @Override
    protected View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_person_center, container, false);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }



}
