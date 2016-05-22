package cn.ifreedomer.beauty.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.facebook.drawee.view.SimpleDraweeView;
import com.zhy.base.adapter.ViewHolder;
import com.zhy.base.adapter.recyclerview.CommonAdapter;

import java.util.List;

import cn.ifreedomer.beauty.R;
import cn.ifreedomer.beauty.entity.User;
import cn.ifreedomer.beauty.entity.jsonbean.ArticleCommon;
import cn.ifreedomer.beauty.util.ImageUtil;
import cn.ifreedomer.beauty.util.IntentUtils;

/**
 * @author:eavawu
 * @date: 5/19/16.
 * @todo:
 */
public class ArticleRecycleViewAdapter extends CommonAdapter<ArticleCommon> {
    private Context context;
    public ArticleRecycleViewAdapter(Context context, int layoutId, List datas) {
        super(context, R.layout.fragment_article_rv_item, datas);
        this.context = context;
    }

    @Override
    public void convert(ViewHolder holder, final ArticleCommon article) {
        SimpleDraweeView avatarSv = holder.getView(R.id.user_circle_iv);
        User user = article.getUser();
        ImageUtil.setFrescoImageView(user.getAvatar(),avatarSv);
        holder.setText(R.id.name_tv,user.getName());
        holder.setText(R.id.title_tv,article.getArticle().getTitle());
        holder.setText(R.id.content_tv,article.getArticle().getContent());
        holder.setOnClickListener(R.id.root_view, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentUtils.startArticleDetailActivity((Activity) context,article);
            }
        });

    }
}
