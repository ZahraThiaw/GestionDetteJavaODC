package org.architecture.Articles.Interface;

import org.architecture.entities.ArticleEntity;
import org.architecture.repositories.Repository;
import org.springframework.stereotype.Component;

public interface ArticleRepository extends Repository<ArticleEntity, Long> {
    ArticleEntity findByLibelle(String libelle);
}
