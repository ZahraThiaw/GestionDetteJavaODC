package org.architecture.Clients.Interface;

import org.architecture.repositories.Repository;
import org.architecture.entities.ClientEntity;

public interface ClientRepository extends Repository<ClientEntity, Long> {
    ClientEntity findByTelephone(String telephone);
}
