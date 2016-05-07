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

import java.lang.reflect.Type;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.adapter.CourseRecycleViewAdapter;
import cn.ifreedomer.beauty.entity.PoplarList;
import cn.ifreedomer.beauty.entity.PopularCourseBean;
import cn.ifreedomer.beauty.network.HttpMethods;
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

    @Override
    public void initView() {
        ButterKnife.bind(this,rootView);
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

//        List<PopularCourseBean> beanArrayList = new ArrayList<>();
//
//        for (int i=0;i<10;i++){
//            PopularCourseBean bean=new PopularCourseBean();
//            beanArrayList.add(bean);
//
//        }

//        recycleview.setAdapter(new CommonAdapter<PopularCourseBean>(getActivity(),R.layout.coursefragment_rv_item,beanArrayList) {
//            @Override
//            public void convert(ViewHolder holder, PopularCourseBean o) {
//                LogUtil.error("CourseRecycleViewAdapter","convert");
//            }


//        });

        HttpMethods.getInstance().getPopularCourseList(new ProgressSubscriber<PoplarList>(popularSubscriber, getActivity()),curPageIndex);

    }

    @Override
    public void setData() {

    }

    public CourseFragment() {
        // Required empty public constructor
    }



    @Override
    protected View onCreateRootView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_course, container, false);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
