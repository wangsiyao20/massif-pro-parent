package com.massif.article.service;

import com.massif.article.entity.Article;
import com.massif.article.entity.model.ArticleModel;

import java.util.List;

public interface IArticleService {

    /**
     * 新增数据
     */
    Boolean addArticle(Article article);

    /**
     * 查询指定频道下的文章列表
     */
    List<ArticleModel> getListByChannelId(String id);
}
