package cn.ifreedomer.beauty.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.Course;
import cn.ifreedomer.beauty.util.DateUtil;
import cn.ifreedomer.beauty.util.ImageUtil;

/**
 * @author:eavawu
 * @date: 5/10/16.
 * @todo:
 */
public class CourseInfoView extends RelativeLayout {
    @Bind(R.id.course_bg_iv)
    SimpleDraweeView courseBgIv;
    @Bind(R.id.course_name_tv)
    TextView courseNameTv;
    @Bind(R.id.course_author_tv)
    TextView courseAuthorTv;
    @Bind(R.id.title_layout)
    LinearLayout titleLayout;
    @Bind(R.id.lessonlike_tv)
    TextView lessonlikeTv;
    @Bind(R.id.course_time_tv)
    TextView courseTimeTv;
    private Context ctx;

    public CourseInfoView(Context context) {
        super(context);
        ctx = context;
        initView();
    }

    public CourseInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(ctx).inflate(R.layout.course_info_item, this);
        ButterKnife.bind(this);
    }
    public void setCourse(Course course) {
        courseTimeTv.setText(DateUtil.getTimeStrBySecond(course.getCourseTime()));
        courseNameTv.setText(course.getCourseName());
        ImageUtil.setFrescoImageView(course.getPic()[0], courseBgIv);
    }
}
