package org.architecture.database;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

@PropertySource("classpath:application.properties")
@Component
public class DatabaseFactory implements Database {

    private final DataSource dataSource;
    private Connection connection;

    public DatabaseFactory(@Value("${db.url}") String dbUrl,
                           @Value("${db.username}") String dbUsername,
                           @Value("${db.password}") String dbPassword) {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        ds.setUrl(dbUrl);
        ds.setUsername(dbUsername);
        ds.setPassword(dbPassword);
        this.dataSource = ds;
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = dataSource.getConnection();
        }
        return connection;
    }

    @Override
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void executeQuery(String query) throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeQuery(query);
        }
    }

    @Override
    public void executeUpdate(String query) throws SQLException {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.executeUpdate(query);
        }
    }
}