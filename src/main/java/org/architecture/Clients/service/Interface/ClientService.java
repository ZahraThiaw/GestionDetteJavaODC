package org.architecture.Clients.service.Interface;

import org.architecture.entities.ClientEntity;

import java.util.List;

public interface ClientService {
    void addClient(ClientEntity client);
    List<ClientEntity> listClients();
}
