package org.architecture.repositories;

import org.architecture.database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@org.springframework.stereotype.Repository
public abstract class CrudRepository<T, ID> implements Repository<T, ID> {
    private final String tablename;
    protected final Database database;

    protected static final Logger logger = Logger.getLogger(CrudRepository.class.getName());

    public CrudRepository(String tablename, Database database) {
        this.tablename = tablename;
        this.database = database;
    }

    protected String getTablename() {
        return tablename;
    }

    protected abstract T createEntityFromResultSet(ResultSet rs) throws SQLException;

    protected abstract String getInsertColumns();

    protected abstract String getUpdateColumns();

    protected abstract void setParametersForInsert(PreparedStatement stmt, T entity) throws SQLException;

    protected abstract void setParametersForUpdate(PreparedStatement stmt, T entity) throws SQLException;

    @Override
    public void save(T entity) {
        String query = "INSERT INTO " + getTablename() + " (" + getInsertColumns() + ") VALUES (?, ?, ?, ?)";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            setParametersForInsert(stmt, entity);
            stmt.executeUpdate();
            logger.info("Entité sauvegardée dans la table " + getTablename());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur lors de la sauvegarde de l'entité", e);
        }
    }

    @Override
    public Iterable<T> findAll() {
        System.out.println("Jdbc");
        List<T> entities = new ArrayList<>();
        String query = "SELECT * FROM " + getTablename() + ";";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                T entity = createEntityFromResultSet(rs);
                entities.add(entity);
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur lors de l'extraction des entités depuis la base de données", e);
        }
        return entities;
    }

    @Override
    public void update(T entity) {
        String query = "UPDATE " + getTablename() + " SET " + getUpdateColumns() + " WHERE id = ?;";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            setParametersForUpdate(stmt, entity);
            stmt.executeUpdate();
            logger.info("Entité mise à jour dans la table " + getTablename());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur lors de la mise à jour de l'entité", e);
        }
    }

    @Override
    public void delete(ID id) {
        String query = "DELETE FROM " + getTablename() + " WHERE id = ?;";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            stmt.executeUpdate();
            logger.info("Entité supprimée de la table " + getTablename());
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur lors de la suppression de l'entité", e);
        }
    }

    @Override
    public T find(ID id) {
        T entity = null;
        String query = "SELECT * FROM " + getTablename() + " WHERE id = ?;";
        try (Connection conn = database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setObject(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    entity = createEntityFromResultSet(rs);
                }
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erreur lors de la recherche de l'entité", e);
        }
        return entity;
    }
}
