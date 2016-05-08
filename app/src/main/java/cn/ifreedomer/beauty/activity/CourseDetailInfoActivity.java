package cn.ifreedomer.beauty.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.base.adapter.recyclerview.DividerItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.adapter.CourseDetailRvAdapter;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.entity.Course;
import cn.ifreedomer.beauty.entity.jsonbean.CourseItems;
import cn.ifreedomer.beauty.entity.jsonbean.PopularCourseBean;
import cn.ifreedomer.beauty.network.HttpMethods;
import cn.ifreedomer.beauty.subscribers.ProgressSubscriber;
import cn.ifreedomer.beauty.subscribers.SubscriberOnNextListener;
import cn.ifreedomer.beauty.util.ImageUtil;
import cn.ifreedomer.beauty.util.TextUtils;

public class CourseDetailInfoActivity extends BaseActivity {

    @Bind(R.id.dancing_title_tv)
    TextView dancingTitleTv;
    @Bind(R.id.dancing_descri_tv)
    TextView dancingDescriTv;
    @Bind(R.id.dancing_jointrain_tv)
    TextView dancingJointrainTv;
    @Bind(R.id.dancing_plan_rv)
    RecyclerView dancingPlanRv;
    @Bind(R.id.course_bg_iv)
    SimpleDraweeView courseBgIv;
    @Bind(R.id.time_tv)
    TextView timeTv;
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
                dancingPlanRv.addItemDecoration(new DividerItemDecoration(CourseDetailInfoActivity.this, DividerItemDecoration.VERTICAL_LIST));
                dancingPlanRv.setAdapter(new CourseDetailRvAdapter(CourseDetailInfoActivity.this,0,courseItems.getCourseItemList()));
            }
        };
        HttpMethods.getInstance().getCourseItems(new ProgressSubscriber<CourseItems>(getCourseItemsSub,this),popCourse.getCourse().getId());
    }

    private void initData() {
        popCourse = (PopularCourseBean) getIntent().getSerializableExtra(IntentConstants.POPCOURSE_BEAN);
        Course course = popCourse.getCourse();
        dancingTitleTv.setText(course.getCourseName());
        dancingDescriTv.setText(course.getCourseDes());
        dancingJointrainTv.setText(String.format(getString(R.string.joinmain_wrap), course.getJoinMan()));
        TextUtils.setWrapTime(timeTv, popCourse.getCourse().getCourseTime(),R.string.course_time_wrap);
//        timeTv.setText(String.format(getString(R.string.course_time), DateUtils.get course.getCourseTime()));
        ImageUtil.setFrescoImageView(popCourse.getCourse().getPic()[0], courseBgIv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
