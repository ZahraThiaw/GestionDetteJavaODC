package org.architecture.Articles.service.Interface;

import org.architecture.entities.ArticleEntity;
import java.util.List;

public interface ArticleService {
    void createArticle(ArticleEntity article);
    List<ArticleEntity> listArticles();
    ArticleEntity findArticleByLibelle(String libelle);
}
