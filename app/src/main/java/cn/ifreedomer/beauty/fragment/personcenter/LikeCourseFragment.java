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
import cn.ifreedomer.beauty.adapter.CourseRecycleViewAdapter;
import cn.ifreedomer.beauty.entity.jsonbean.CourseList;
import cn.ifreedomer.beauty.fragment.BaseFragment;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;

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
        SubscriberOnNextListener<CourseList> popularListSubscriberOnNextListener = new SubscriberOnNextListener<CourseList>() {
            @Override
            public void onNext(CourseList courseList) {
                CourseRecycleViewAdapter courseRecycleViewAdapter = new CourseRecycleViewAdapter(getActivity(),0,courseList.getCourseList());
                recycleview.setLayoutManager(new LinearLayoutManager(getActivity()));
                recycleview.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
                CourseRecycleViewAdapter adapter = new CourseRecycleViewAdapter(getActivity(),R.layout.coursefragment_rv_item, courseList.getCourseList());
                recycleview.setAdapter(adapter);
            }
        };

        HttpMethods.getInstance().getLikeCourseList(new ProgressSubscriber<CourseList>(popularListSubscriberOnNextListener,getActivity()),1);

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
