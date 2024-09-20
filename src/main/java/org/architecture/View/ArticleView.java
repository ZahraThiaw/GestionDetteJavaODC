package org.architecture.View;

import org.architecture.Articles.service.Interface.ArticleService;
import org.architecture.entities.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Scanner;

@Component
public class ArticleView {

    private final ArticleService articleService;
    private final Scanner scanner = new Scanner(System.in);

    @Autowired
    public ArticleView(ArticleService articleService) {
        this.articleService = articleService;
    }

    public void addArticle() {
        System.out.print("Entrez le libelle de l'article : ");
        String libelle = scanner.nextLine();
        System.out.print("Entrez le prix de l'article : ");
        double prix = scanner.nextDouble();
        System.out.print("Entrez la quantité en stock : ");
        int qteStock = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        ArticleEntity article = new ArticleEntity();
        article.setLibelle(libelle);
        article.setPrix(prix);
        article.setQteStock(qteStock);

        articleService.createArticle(article);
        System.out.println("Article ajouté avec succès !");
    }

    public void listArticles() {
        List<ArticleEntity> articles = articleService.listArticles();
        if (articles.isEmpty()) {
            System.out.println("Aucun article trouvé.");
        } else {
            for (ArticleEntity article : articles) {
                System.out.println("ID: " + article.getId() + ", Libelle: " + article.getLibelle() + ", Prix: " + article.getPrix() + ", Quantité en stock: " + article.getQteStock());
            }
        }
    }

    public void filterArticlesByLibelle() {
        System.out.print("Entrez le libelle de l'article à filtrer : ");
        String libelle = scanner.nextLine();
        ArticleEntity article = articleService.findArticleByLibelle(libelle);
        if (article != null) {
            System.out.println("ID: " + article.getId() + ", Libelle: " + article.getLibelle() + ", Prix: " + article.getPrix() + ", Quantité en stock: " + article.getQteStock());
        } else {
            System.out.println("Aucun article trouvé avec le libelle : " + libelle);
        }
    }
}
