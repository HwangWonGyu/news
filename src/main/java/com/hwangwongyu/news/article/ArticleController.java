package com.hwangwongyu.news.article;

import com.hwangwongyu.news.login.CheckLogin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ArticleController {

    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Value("${spring.webservice.newsHome}")
    private String newsHomeURL;

    @PostMapping("/articles")
    @CheckLogin
    public String addArticle(@Valid @RequestBody ArticleDTO article, BindingResult result) {
        if (result.hasErrors()) {
            return "articles/createOrUpdateArticleForm";
        } else {
            articleService.addArticle(article);
            return "redirect:" + newsHomeURL;
        }
    }

    @GetMapping("/articles/{id}")
    public ArticleDTO findArticleById(@PathVariable long id) {
        ArticleDTO article = articleService.findArticleById(id);
        if(article != null)
            return article;
        else
            return null;
    }

}
