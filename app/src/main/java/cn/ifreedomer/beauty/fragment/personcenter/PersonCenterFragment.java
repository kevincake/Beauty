package cn.ifreedomer.beauty.fragment.personcenter;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.PopupMenu;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.adapter.ViewPagerFragmentAdapter;
import cn.ifreedomer.beauty.entity.User;
import cn.ifreedomer.beauty.entity.jsonbean.FollowListResult;
import cn.ifreedomer.beauty.fragment.BaseFragment;
import cn.ifreedomer.beauty.manager.AppManager;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.ImageUtil;
import cn.ifreedomer.beauty.util.IntentUtils;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonCenterFragment extends BaseFragment implements MaterialTabListener {


    @Bind(R.id.course_bg_iv)
    SimpleDraweeView courseBgIv;
    @Bind(R.id.person_avatar_sv)
    SimpleDraweeView personAvatarSv;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.fans_tv)
    TextView fansTv;
    @Bind(R.id.mine_follows_tv)
    TextView mineFollowsTv;
    @Bind(R.id.paint_iv)
    ImageView paintIv;
    @Bind(R.id.tabHost)
    MaterialTabHost tabHost;
    @Bind(R.id.person_more_iv)
    ImageView personMoreIv;
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    private List<User> followEntityList;
    private List<User> beFollowEntityList;
    private List<Fragment> personfragments = new ArrayList<>();
    private int [] titles = new int[]{R.string.like_course,R.string.comments};

    @Override
    public void initView() {
        ButterKnife.bind(this, rootView);


        nameTv.setText(AppManager.getInstance().getUser().getName());
        ImageUtil.setFrescoImageView(AppManager.getInstance().getUser().getAvatar(), personAvatarSv);
        initFragments();
        initPageAndTabHost();
    }

    @Override
    public void setData() {
        SubscriberOnNextListener<FollowListResult> subFollowList = new SubscriberOnNextListener<FollowListResult>() {
            @Override
            public void onNext(FollowListResult followList) {
                followEntityList = followList.getFollowEntities();
                beFollowEntityList = followList.getBeFollowEntities();
            }
        };
        HttpMethods.getInstance().getFollowList(new ProgressSubscriber<FollowListResult>(subFollowList, getActivity()));



    }


    private void showMenuPop(View anchorView) {
        PopupMenu popupMenu = new PopupMenu(getActivity(), personMoreIv);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.person_more_menu, popupMenu.getMenu());
        popupMenu.show();

//        View view = View.inflate(getActivity(), R.layout.person_menu_more, null);
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

    public PersonCenterFragment() {
        // Required empty public constructor
    }



    private void initFragments() {
        personfragments.add(new MineCommentsFragment());
        personfragments.add(new LikeCourseFragment());



    }



    private void initPageAndTabHost() {
        // init view pager
        ViewPagerFragmentAdapter pagerAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), personfragments);
        viewpager.setAdapter(pagerAdapter);
        viewpager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                // when user do a swipe the selected tab change
                tabHost.setSelectedNavigationItem(position);
            }
        });
        // insert all tabs from pagerAdapter data
        for (int i = 0; i < pagerAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(getString(titles[i]))
                            .setTabListener(this)
            );
        }
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

    @OnClick({R.id.course_bg_iv, R.id.person_avatar_sv, R.id.fans_tv, R.id.mine_follows_tv, R.id.paint_iv, R.id.person_more_iv})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.course_bg_iv:
                break;
            case R.id.person_avatar_sv:
                break;
            case R.id.fans_tv:
                break;
            case R.id.mine_follows_tv:
                break;
            case R.id.paint_iv:
                IntentUtils.startEditProfileActivity(getActivity());
                break;
            case R.id.person_more_iv:
                showMenuPop(view);
//                IntentUtils.startSettingActivity(getActivity());
                break;
        }
    }




    @Override
    public void onTabSelected(MaterialTab tab) {
        viewpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabReselected(MaterialTab tab) {

    }

    @Override
    public void onTabUnselected(MaterialTab tab) {

    }
}
