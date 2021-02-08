package com.hwangwongyu.news.article;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ArticleMapper {

    Integer addArticle(ArticleDTO article);

    ArticleDTO findArticleById(long id);
}
