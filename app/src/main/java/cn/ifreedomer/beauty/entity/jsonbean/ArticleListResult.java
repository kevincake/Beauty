package cn.ifreedomer.beauty.entity.jsonbean;

import java.util.List;

/**
 * @author:eavawu
 * @date: 5/19/16.
 * @todo:
 */
public class ArticleListResult {
    List<ArticleCommon> articleList;

    public List<ArticleCommon> getArticleList() {
        return articleList;
    }

    public void setArticleList(List<ArticleCommon> articleList) {
        this.articleList = articleList;
    }
}
