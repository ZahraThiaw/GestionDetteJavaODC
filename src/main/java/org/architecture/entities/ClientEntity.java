package org.architecture.entities;

import lombok.*;

//@Data
@Getter
@Setter
public class ClientEntity {
    private Long id;
    private String surname;
    private String telephone;
    private String adresse;
    private Long userId; // Lien vers le compte utilisateur (s'il existe)
}
