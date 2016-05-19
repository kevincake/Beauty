package cn.ifreedomer.beauty.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.listener.OnClickCommentListener;

/**
 * @author:eavawu
 * @date: 5/15/16.
 * @todo:
 */
public class CommentInputView extends RelativeLayout implements View.OnClickListener{
    private OnClickCommentListener onClickCommentListener;
    @Bind(R.id.send_ib)
    ImageButton sendIb;
    @Bind(R.id.content_et)
    EditText contentEt;

    public OnClickCommentListener getOnClickCommentListener() {
        return onClickCommentListener;
    }

    public void setOnClickCommentListener(OnClickCommentListener onClickCommentListener) {
        this.onClickCommentListener = onClickCommentListener;
    }

    public CommentInputView(Context context) {
        super(context);
        initView(context);
    }

    public CommentInputView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.comment_input_view, this);
        ButterKnife.bind(this);
    }


    @Override
    @OnClick({R.id.send_ib})
    public void onClick(View view) {
        if (onClickCommentListener!=null){
            onClickCommentListener.onClickSend(view);
        }
    }
}
