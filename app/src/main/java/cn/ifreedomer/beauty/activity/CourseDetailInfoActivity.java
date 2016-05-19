package cn.ifreedomer.beauty.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.adapter.CourseDetailRvAdapter;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.entity.jsonbean.CourseItems;
import cn.ifreedomer.beauty.entity.jsonbean.PopularCourseBean;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.widget.CommonItemDecorate;
import cn.ifreedomer.beauty.widget.CourseInfoView;

public class CourseDetailInfoActivity extends BaseActivity {


    @Bind(R.id.course_des_tv)
    TextView dancingDescriTv;

    @Bind(R.id.course_info_view)
    CourseInfoView courseInfoView;
    @Bind(R.id.dancing_plan_rv)
    RecyclerView dancingPlanRv;

    private PopularCourseBean popCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail_info);
        ButterKnife.bind(this);
        initData();
        initNetWork();
    }

    private void initNetWork() {
        SubscriberOnNextListener<CourseItems> getCourseItemsSub = new SubscriberOnNextListener<CourseItems>() {
            @Override
            public void onNext(CourseItems courseItems) {
                dancingPlanRv.setLayoutManager(new LinearLayoutManager(CourseDetailInfoActivity.this));
                dancingPlanRv.addItemDecoration(new CommonItemDecorate(CourseDetailInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
                dancingPlanRv.setAdapter(new CourseDetailRvAdapter(CourseDetailInfoActivity.this, 0, courseItems.getCourseItemList()));
            }
        };
        HttpMethods.getInstance().getCourseItems(new ProgressSubscriber<CourseItems>(getCourseItemsSub, this), popCourse.getCourse().getId());
    }

    private void initData() {
        popCourse = (PopularCourseBean) getIntent().getSerializableExtra(IntentConstants.POPCOURSE_BEAN);
        courseInfoView.setCourse(popCourse.getCourse());
        dancingDescriTv.setText(popCourse.getCourse().getCourseDes());

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
