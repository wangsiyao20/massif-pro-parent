package com.massif.article.service.impl;

import com.massif.article.entity.Article;
import com.massif.article.entity.model.ArticleModel;
import com.massif.article.mapper.ArticleMapper;
import com.massif.article.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 新增数据
     */
    public Boolean addArticle(Article article) {
        article.setId(UUID.randomUUID().toString().substring(0, 20));
        articleMapper.addArticle(article);
        return true;
    }

    /**
     * 查询指定频道下的文章列表
     */
    public List<ArticleModel> getListByChannelId(String id) {
        return articleMapper.getListByChannelId(id);
    }
}
