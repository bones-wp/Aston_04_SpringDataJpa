package org.example.configurations;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

/**
 * реализация паттерна Singleton
 */

@Component
public class JdbcConnection {

    private static Optional<Connection> connection = Optional.empty();

    public static Optional<Connection> getConnection() {
        if (connection.isEmpty()) {
            String driver = "org.postgresql.Driver";
            String url = "jdbc:postgresql://localhost:5432/postgres";
            String user = "postgres";
            String password = "root";

            try {
                Class.forName(driver);
                connection = Optional.ofNullable(
                        DriverManager.getConnection(url, user, password));
            } catch (SQLException ex) {
                ex.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        return connection;
    }
}