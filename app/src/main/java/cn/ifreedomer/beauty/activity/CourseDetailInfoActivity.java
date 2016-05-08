package cn.ifreedomer.beauty.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.entity.Course;
import cn.ifreedomer.beauty.entity.PopularCourseBean;
import cn.ifreedomer.beauty.util.ImageUtil;

public class CourseDetailInfoActivity extends BaseActivity {

    @Bind(R.id.dancing_title_tv)
    TextView dancingTitleTv;
    @Bind(R.id.dancing_descri_tv)
    TextView dancingDescriTv;
    @Bind(R.id.dancing_jointrain_tv)
    Button dancingJointrainTv;
    @Bind(R.id.dancing_plan_rv)
    RecyclerView dancingPlanRv;
    @Bind(R.id.course_bg_iv)
    SimpleDraweeView courseBgIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail_info);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        PopularCourseBean popCourse = (PopularCourseBean) getIntent().getSerializableExtra(IntentConstants.POPCOURSE_BEAN);
        Course course = popCourse.getCourse();
        dancingTitleTv.setText(course.getCourseName());
        dancingDescriTv.setText(course.getCourseDes());
        ImageUtil.setFrescoImageView(popCourse.getCourse().getPic()[0],courseBgIv);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }
}
