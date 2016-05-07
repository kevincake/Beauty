package cn.ifreedomer.beauty.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.adapter.CourseRecycleViewAdapter;
import cn.ifreedomer.beauty.entity.PoplarList;
import cn.ifreedomer.beauty.entity.PopularCourseBean;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.notifycation.FollowEvent;
import cn.ifreedomer.beauty.notifycation.NotifycationManager;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseFragment extends BaseFragment {


    @Bind(R.id.recycleview)
    RecyclerView recycleview;
    private SubscriberOnNextListener<PoplarList> popularSubscriber;
    private int curPageIndex = 1;
    private SubscriberOnNextListener followStatusSubscriber;

    @Override
    public void initView() {
        ButterKnife.bind(this,rootView);
        NotifycationManager.getInstance().register(this);
        final Type type = new TypeToken<PoplarList>() {
        }.getType();
        popularSubscriber = new SubscriberOnNextListener<PoplarList>() {
            @Override
            public void onNext(PoplarList popularCourseList) {
                List<PopularCourseBean> courseList = popularCourseList.getCourseList();
                recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycleview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
                CourseRecycleViewAdapter adapter = new CourseRecycleViewAdapter(getActivity(),R.layout.coursefragment_rv_item,courseList);
                recycleview.setAdapter(adapter);

            }
        };

        //followStatus
        followStatusSubscriber = new SubscriberOnNextListener() {
            @Override
            public void onNext(Object o) {

            }
        };

        HttpMethods.getInstance().getPopularCourseList(new ProgressSubscriber<PoplarList>(popularSubscriber, getActivity()),curPageIndex);

    }

    @Override
    public void setData() {

    }

    public CourseFragment() {
        // Required empty public constructor
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void followEventReceive(FollowEvent followEvent){
        HttpMethods.getInstance().postFollowStatus(new ProgressSubscriber(followStatusSubscriber,getActivity()),followEvent.getUserId(),followEvent.getFollowStatus());
    }


    @Override
    protected View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course, container, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
        NotifycationManager.getInstance().unregister(this);
    }
}
