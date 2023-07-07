package com.massif.article.entity.model;

import com.massif.article.entity.Article;
import com.massif.article.entity.Channel;
import lombok.Data;

@Data
public class ArticleModel extends Article {

    private Channel channel;

}
