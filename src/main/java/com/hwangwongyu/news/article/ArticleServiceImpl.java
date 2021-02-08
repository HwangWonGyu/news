package com.hwangwongyu.news.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleServiceImpl implements ArticleService {

    private final ArticleMapper articleMapper;

    @Autowired
    ArticleServiceImpl(ArticleMapper articleMapper) {
        this.articleMapper = articleMapper;
    }

    @Override
    public Integer addArticle(ArticleDTO article) {
        return articleMapper.addArticle(article);
    }

    @Override
    public ArticleDTO findArticleById(long id) {
        return articleMapper.findArticleById(id);
    }
}
