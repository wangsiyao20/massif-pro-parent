package com.massif.article.mapper;


import com.massif.article.entity.Article;
import com.massif.article.entity.model.ArticleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ArticleMapper {


    /**
     * 新增数据
     */
    int addArticle(@Param("article") Article article);

    /**
     * 根据频道查询出文章
     */
    List<ArticleModel> getListByChannelId(@Param("id") String id);
}
