package org.architecture.Clients.JDBC;

import org.architecture.database.Database;
import org.architecture.entities.ClientEntity;
import org.architecture.Clients.Interface.ClientRepository;
import org.architecture.repositories.CrudRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.util.logging.Level;


@Profile("database")
@Repository
@Primary
public class ClientRepoImplJDBC extends CrudRepository<ClientEntity, Long> implements ClientRepository {

    public ClientRepoImplJDBC(Database database) {
        super("clients", database); // Spécifiez le nom de la table des clients
        System.out.println("JDBC");
    }

    @Override
    protected ClientEntity createEntityFromResultSet(ResultSet rs) throws SQLException {
        ClientEntity client = new ClientEntity();
        client.setId(rs.getLong("id"));
        client.setSurname(rs.getString("surname"));
        client.setTelephone(rs.getString("telephone"));
        client.setAdresse(rs.getString("adresse"));
        client.setUserId(rs.getLong("user_id"));
        return client;
    }

    @Override
    protected String getInsertColumns() {
        return "surname, telephone, adresse, user_id"; // Spécifiez les colonnes réelles pour l'insertion
    }

    @Override
    protected String getUpdateColumns() {
        return "surname = ?, telephone = ?, adresse = ?, user_id = ?"; // Spécifiez les colonnes réelles pour la mise à jour
    }

    @Override
    protected void setParametersForInsert(PreparedStatement stmt, ClientEntity client) throws SQLException {
        stmt.setString(1, client.getSurname());
        stmt.setString(2, client.getTelephone());
        stmt.setString(3, client.getAdresse());

        // Si le user_id est null, insérer NULL dans la base de données, sinon insérer l'ID.
        if (client.getUserId() != null) {
            stmt.setLong(4, client.getUserId());
        } else {
            stmt.setNull(4, java.sql.Types.BIGINT);
        }
    }

    @Override
    protected void setParametersForUpdate(PreparedStatement stmt, ClientEntity client) throws SQLException {
        stmt.setString(1, client.getSurname());
        stmt.setString(2, client.getTelephone());
        stmt.setString(3, client.getAdresse());

        // Si le user_id est null, insérer NULL dans la base de données, sinon insérer l'ID.
        if (client.getUserId() != null) {
            stmt.setLong(4, client.getUserId());
        } else {
            stmt.setNull(4, java.sql.Types.BIGINT);
        }

        stmt.setLong(5, client.getId()); // Assurez-vous que l'ID est défini pour la mise à jour
    }

    @Override
    public ClientEntity findByTelephone(String telephone) {
        ClientEntity client = null;
        String query = "SELECT * FROM " + getTablename() + " WHERE telephone = ?;";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, telephone);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    client = createEntityFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur lors de la recherche du client par téléphone", e);
        }

        return client;
    }
}
