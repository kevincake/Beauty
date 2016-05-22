package cn.ifreedomer.beauty.widget;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.User;
import cn.ifreedomer.beauty.entity.jsonbean.FollowListResult;
import cn.ifreedomer.beauty.manager.AppManager;
import cn.ifreedomer.beauty.util.ImageUtil;
import cn.ifreedomer.beauty.util.IntentUtils;

/**
 * @author:eavawu
 * @date: 5/22/16.
 * @todo:
 */
public class PersonHeaderView extends RelativeLayout {
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
    @Bind(R.id.person_more_iv)
    ImageView personMoreIv;
    private Context context;

    public PersonHeaderView(Context context) {
        super(context);
    }

    public PersonHeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    private void initView(Context context) {
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.person_headerview, this);
        ButterKnife.bind(this);
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
                IntentUtils.startEditProfileActivity((Activity) context);
                break;
            case R.id.person_more_iv:
//                showMenuPop(view);
//                IntentUtils.startSettingActivity(getActivity());
                break;
        }
    }

    public void setUser(User user){
        nameTv.setText(AppManager.getInstance().getUser().getName());
        ImageUtil.setFrescoImageView(AppManager.getInstance().getUser().getAvatar(), personAvatarSv);
    }

    public void setFollowData(FollowListResult followData){

    };



}
