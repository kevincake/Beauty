package cn.ifreedomer.beauty.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.entity.jsonbean.SocialDetailBean;
import cn.ifreedomer.beauty.listener.OnClickCommentListener;
import cn.ifreedomer.beauty.widget.CommentInputView;
import cn.ifreedomer.beauty.widget.SocialInfoView;

public class CommentActivity extends BaseActivity implements OnClickCommentListener{

    @Bind(R.id.social_info_view)
    SocialInfoView socialInfoView;
    @Bind(R.id.comment_rv)
    RecyclerView commentRv;
    @Bind(R.id.comment_input_view)
    CommentInputView commentInputView;
    private SocialDetailBean socialDetailBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        ButterKnife.bind(this);
        initData();
        initView();
        initNetWork();

    }

    private void initNetWork() {

    }

    private void initView() {

    }

    private void initData() {
        socialDetailBean = (SocialDetailBean) getIntent().getSerializableExtra(IntentConstants.SOCIAL_BEAN);
        socialInfoView.setSocialDetail(socialDetailBean);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    @Override
    public void onClickSend(View view) {

    }
}
