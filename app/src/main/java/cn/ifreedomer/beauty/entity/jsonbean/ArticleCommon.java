package cn.ifreedomer.beauty.entity.jsonbean;

import java.io.Serializable;

import cn.ifreedomer.beauty.entity.Article;
import cn.ifreedomer.beauty.entity.User;

/**
 * @atuhor:eavawu
 * @date:5/19/16
 * @todo:
 */
public class ArticleCommon implements Serializable{
    private Article article;
    private User user;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
