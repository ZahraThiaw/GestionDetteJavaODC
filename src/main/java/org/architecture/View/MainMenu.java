package org.architecture.View;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MainMenu {
    private final ClientView clientView;
    //private final ArticleView articleView;
    //private final DetteView detteView; // Ajoutez les vues nécessaires pour gérer les dettes et paiements
    //private final PaiementView paiementView;

    @Autowired
    public MainMenu(ClientView clientView, ArticleView articleView) {
        this.clientView = clientView;
        //this.articleView = articleView;
        //this.detteView = detteView;
        //this.paiementView = paiementView;
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("=== Menu Principal ===");
            System.out.println("1 - Ajouter Client");
            System.out.println("2 - Lister Clients");
            System.out.println("3 - Créer un compte utilisateur pour un client");
            System.out.println("4 - Ajouter un article");
            System.out.println("5 - Lister les articles");
            System.out.println("6 - Créer une Dette pour un Client");
            System.out.println("7 - Effectuer un Paiement");
            System.out.println("8 - Lister les dettes d'un client");
            System.out.println("9 - Lister les paiements d'une dette");
            System.out.println("0 - Quitter");
            System.out.print("Choisissez une option: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choice) {
                case 1:
                    clientView.addClient(scanner); // Ajouter un client
                    break;

                case 2:
                    clientView.listClients(); // Lister les clients
                    break;

                case 3:
                    //createClientAccount(scanner); // Créer un compte utilisateur pour un client
                    break;

                case 4:
                    //articleView.addArticle(); // Ajouter un article
                    break;

                case 5:
                   // articleView.listArticles(); // Lister les articles
                    break;

                case 6:
                    //detteView.createDetteForClient(scanner); // Créer une dette pour un client
                    break;

                case 7:
                   // paiementView.effectuerPaiement(scanner); // Effectuer un paiement
                    break;

                case 8:
                    //detteView.listerDettesClient(scanner); // Lister les dettes d'un client
                    break;

                case 9:
                    //paiementView.listerPaiementsDette(scanner); // Lister les paiements d'une dette
                    break;

                case 0:
                    System.out.println("Au revoir !");
                    break;

                default:
                    System.out.println("Option invalide. Essayez encore.");
            }
        } while (choice != 0);

        scanner.close();
    }
}
