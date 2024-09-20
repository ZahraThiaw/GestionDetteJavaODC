package org.architecture.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface Database {

    // Méthode pour obtenir une connexion à la base de données
    Connection getConnection() throws SQLException;

    // Méthode pour fermer la connexion à la base de données
    void closeConnection() throws SQLException;

    // Méthode pour exécuter une requête SQL et retourner un ResultSet
    void executeQuery(String query) throws SQLException;

    // Méthode pour exécuter une mise à jour SQL et retourner le nombre de lignes affectées
    void executeUpdate(String query) throws SQLException;
}
