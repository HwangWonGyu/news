package com.hwangwongyu.news.article;

public interface ArticleService {

    Integer addArticle(ArticleDTO article);

    ArticleDTO findArticleById(long id);

}
