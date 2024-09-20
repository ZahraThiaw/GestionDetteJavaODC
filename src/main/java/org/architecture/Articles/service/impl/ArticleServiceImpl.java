package org.architecture.Articles.service.impl;

import org.architecture.Articles.Interface.ArticleRepository;
import org.architecture.Articles.service.Interface.ArticleService;
import org.architecture.entities.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticleServiceImpl(@Qualifier("articleRepositoryCollection") ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void createArticle(ArticleEntity article) {
        articleRepository.save(article);
    }

    @Override
    public List<ArticleEntity> listArticles() {
        return (List<ArticleEntity>) articleRepository.findAll();
    }

    @Override
    public ArticleEntity findArticleByLibelle(String libelle) {
        return articleRepository.findByLibelle(libelle);
    }
}
