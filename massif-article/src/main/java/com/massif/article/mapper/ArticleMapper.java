package com.massif.article.mapper;


import com.massif.article.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ArticleMapper {


    /**
     * 新增数据
     */
    int addArticle(@Param("article") Article article);
}
