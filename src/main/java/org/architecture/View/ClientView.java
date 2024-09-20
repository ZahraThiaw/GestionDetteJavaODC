package org.architecture.View;

import org.architecture.Clients.service.Interface.ClientService;
import org.architecture.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ClientView {
    private final ClientService clientService;

    @Autowired
    public ClientView(ClientService clientService) {
        this.clientService = clientService;
    }

    public void addClient(Scanner scanner) {
        System.out.print("Entrez le surnom du client: ");
        String surname = scanner.nextLine();
        System.out.print("Entrez le téléphone du client: ");
        String telephone = scanner.nextLine();
        System.out.print("Entrez l'adresse du client: ");
        String adresse = scanner.nextLine();
        System.out.print("Entrez l'ID utilisateur (ou 0 si aucun compte): ");
        Long userId = scanner.nextLong();
        scanner.nextLine(); // Consume newline

        ClientEntity newClient = new ClientEntity();
        newClient.setSurname(surname);
        newClient.setTelephone(telephone);
        newClient.setAdresse(adresse);
        newClient.setUserId(userId == 0 ? null : userId);

        clientService.addClient(newClient);
        System.out.println("Client ajouté.");
    }

    public void listClients() {
        List<ClientEntity> clients = clientService.listClients();
        for (ClientEntity client : clients) {
            System.out.println("ID: " + client.getId() + ", Prénom: " + client.getSurname() +
                    ", Téléphone: " + client.getTelephone() +
                    ", Adresse: " + client.getAdresse() +
                    ", ID Utilisateur: " + client.getUserId());
        }
    }
}
