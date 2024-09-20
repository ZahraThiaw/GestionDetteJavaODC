package org.architecture.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class ArticleEntity {
    private Long id;
    private String libelle;
    private double prix;
    private int qteStock;
}
