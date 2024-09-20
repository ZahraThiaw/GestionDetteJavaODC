package org.architecture.Clients.service.impl;

import org.architecture.Clients.Interface.ClientRepository;
import org.architecture.Clients.service.Interface.ClientService;
import org.architecture.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

//    @Override
//    public void addClient(ClientEntity client) {
//        clientRepository.save(client);
//    }

    @Override
    public void addClient(ClientEntity client) {
        clientRepository.save(client);
        System.out.println("Client ajouté : " + client);
    }


    @Override
    public List<ClientEntity> listClients() {
        // Convertir l'Iterable en List
        return StreamSupport.stream(clientRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public ClientEntity findClientByTelephone(String telephone) {
        return clientRepository.findByTelephone(telephone);
    }
}
