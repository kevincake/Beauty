package cn.ifreedomer.beauty.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.listener.TextCheckBoxListener;

/**
 * @author:eavawu
 * @date: 5/7/16.
 * @todo:
 */
public class TextCheckBox extends RelativeLayout {
    private TextCheckBoxListener textCheckBoxListener;
    private int btnImg = R.drawable.follow_cb;
    private String textStr = "follow";
    private CheckBox cb;
    private TextView tv;

    public TextCheckBox(Context context) {
        super(context);
        initView(context);
    }

    public TextCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.textcheckbox);
        btnImg = typedArray.getInt(R.styleable.textcheckbox_selector, R.drawable.follow_cb);
        textStr = getResources().getString(typedArray.getInt(R.styleable.textcheckbox_text, R.string.follow));
        initView(context);
    }


    public void initView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.text_checkbox, this);
        tv = (TextView) findViewById(R.id.textview);
        tv.setText(textStr);
        cb = (CheckBox) findViewById(R.id.checkbox);
        cb.setButtonDrawable(btnImg);
        cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                tv.setVisibility(b? View.GONE : View.VISIBLE);
                if (textCheckBoxListener!=null){
                    textCheckBoxListener.onCheckChangeListener(b);
                }
            }
        });
    }
    public void setTextCheckBoxListener(TextCheckBoxListener textCheckBoxListener){
        this.textCheckBoxListener = textCheckBoxListener;
    }

    public void setButton(int resId) {
        cb.setButtonDrawable(resId);
    }

    public void setText(String text) {
        tv.setText(text);
    }

}
