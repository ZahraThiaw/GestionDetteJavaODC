package org.architecture.Articles.collection;

import org.architecture.Articles.Interface.ArticleRepository;
import org.architecture.entities.ArticleEntity;
import org.architecture.repositories.CollectionRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class ArticleRepositoryCollection extends CollectionRepository<ArticleEntity, Long> implements ArticleRepository {
    private final Map<String, ArticleEntity> articlesByLibelle = new HashMap<>();

    @Override
    protected Long generateId() {
        return idCounter.incrementAndGet(); // idCounter est maintenant accessible
    }

    @Override
    protected Long getId(ArticleEntity entity) {
        return entity.getId();
    }

    @Override
    protected void setId(ArticleEntity entity, Long id) {
        entity.setId(id);
    }

    @Override
    public ArticleEntity findByLibelle(String libelle) {
        return articlesByLibelle.get(libelle);
    }

    @Override
    public void save(ArticleEntity article) {
        super.save(article); // Utilise la méthode save héritée de CollectionRepository
        articlesByLibelle.put(article.getLibelle(), article);
    }
}
