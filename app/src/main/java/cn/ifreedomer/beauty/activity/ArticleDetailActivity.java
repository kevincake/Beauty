package cn.ifreedomer.beauty.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.activity.base.BaseActivity;
import cn.ifreedomer.beauty.constants.IntentConstants;
import cn.ifreedomer.beauty.entity.Article;
import cn.ifreedomer.beauty.entity.User;
import cn.ifreedomer.beauty.entity.jsonbean.ArticleCommon;
import cn.ifreedomer.beauty.util.ImageUtil;
import zhou.widget.RichText;

public class ArticleDetailActivity extends BaseActivity {

    @Bind(R.id.user_circle_iv)
    SimpleDraweeView userCircleIv;
    @Bind(R.id.name_tv)
    TextView nameTv;
    @Bind(R.id.tag_tv)
    TextView tagTv;
    @Bind(R.id.html_tv)
    RichText htmlTv;
    @Bind(R.id.title_tv)
    TextView titleTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_detail_fragment);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        Intent intent = getIntent();
        ArticleCommon articleCommon = (ArticleCommon) intent.getSerializableExtra(IntentConstants.ARTICLE_COMMON);
        User user = articleCommon.getUser();
        Article article = articleCommon.getArticle();
        ImageUtil.setFrescoImageView(user.getAvatar(), userCircleIv);
        nameTv.setText(user.getName());
        tagTv.setText("小小小画家");
        titleTv.setText(article.getTitle());


        htmlTv.setOnImageClickListener(new RichText.OnImageClickListener() {
            @Override
            public void imageClicked(List<String> imageUrls, int position) {
                Toast.makeText(getApplicationContext(), imageUrls.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        htmlTv.setOnURLClickListener(new RichText.OnURLClickListener() {
            @Override
            public boolean urlClicked(String url) {
                Toast.makeText(getApplicationContext(), url, Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        htmlTv.setImageFixListener(new RichText.ImageFixListener() {
            @Override
            public void onFix(RichText.ImageHolder holder) {
                if (holder.getWidth() > 10 || holder.getHeight() > 10) {
                    int width = getScreenWidth(getApplicationContext());
                    int height = width;
                    holder.setWidth(width);
                    holder.setHeight(height);
                    holder.setScaleType(RichText.ImageHolder.CENTER_INSIDE);
                }
            }
        });

        htmlTv.setRichText(article.getContent());

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.article_detail_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        return metrics.widthPixels;
    }
}
