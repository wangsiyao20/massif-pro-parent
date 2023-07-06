package com.massif.article.controller;


import com.massif.article.entity.Article;
import com.massif.article.service.IArticleService;
import com.massif.common.entity.Result;
import com.massif.common.entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

    @Autowired
    private IArticleService articleService;


    @GetMapping("/test")
    public String test(){
        Article article = new Article();
        article.setId("1562");
        System.out.println(article.getId());
        return article.getId();
    }

    /**
     * 新增
     */
    @PostMapping(value = "/add")
    public Result<Boolean> addArticle(@RequestBody Article article){
        return new Result<Boolean>(true, StatusCode.SUCCESS, articleService.addArticle(article));
    }

}
