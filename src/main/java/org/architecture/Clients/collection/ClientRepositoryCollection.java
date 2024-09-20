package org.architecture.Clients.collection;

import org.architecture.entities.ClientEntity;
import org.architecture.Clients.Interface.ClientRepository;
import org.architecture.repositories.CollectionRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;


@Profile("collection")
@Repository
@Primary
public class ClientRepositoryCollection extends CollectionRepository<ClientEntity, Long> implements ClientRepository {

    private final Set<ClientEntity> clients;

    public ClientRepositoryCollection(Set<ClientEntity> clients) {
        this.clients = clients;
        initializeDataStore();
        System.out.println("IN-MEMORY");
    }

    private void initializeDataStore() {
        for (ClientEntity client : clients) {
            if (client != null) {
                save(client);
            }
        }
    }

    @Override
    protected Long generateId() {
        return idCounter.incrementAndGet();
    }

    @Override
    protected Long getId(ClientEntity client) {
        return client.getId();
    }

    @Override
    protected void setId(ClientEntity client, Long id) {
        client.setId(id);
    }

    @Override
    public ClientEntity findByTelephone(String telephone) {
        Optional<ClientEntity> clientOpt = clients.stream()
                .filter(client -> telephone.equals(client.getTelephone()))
                .findFirst();
        return clientOpt.orElse(null);
    }
}
