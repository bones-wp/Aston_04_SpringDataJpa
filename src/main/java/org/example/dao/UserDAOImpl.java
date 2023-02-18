package org.example.dao;

import lombok.Data;
import org.example.entity.User;
import org.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
@Data
public class UserDAOImpl implements UserDAO {
    private final String driver;
    private final String url;
    private final String username;
    private final String password;

    public UserDAOImpl(@Value("${database.driver}") String driver,
                       @Value("${database.url}") String url,
                       @Value("${database.username}") String username,
                       @Value("${database.password}") String password) {
        this.driver = driver;
        this.url = url;
        this.username = username;
        this.password = password;

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getUsers() {
        String query = "SELECT * FROM users";
        List<User> result = new ArrayList<>();

        try (Connection connection = DriverManager.getConnection(url, username, password);
             Statement st = connection.createStatement()) {
            ResultSet data = st.executeQuery(query);

            while (data.next()) {
                result.add(
                        new User(
                                data.getLong("id"),
                                data.getString("name")
                        )
                );
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return result;
//        final List<User> users = userRepo.findAll();
//        return users;
    }

//    public static Optional<Connection> getConnection() {
//        if (connection.isEmpty()) {
//            String driver = "org.postgresql.Driver";
//            String url = "jdbc:postgresql://localhost:5432/postgres";
//            String user = "postgres";
//            String password = "root";
//
//            try {
//                Class.forName(driver);
//                connection = Optional.ofNullable(
//                        DriverManager.getConnection(url, user, password));
//            } catch (SQLException ex) {
//                ex.printStackTrace();
//            } catch (ClassNotFoundException e) {
//                throw new RuntimeException(e);
//            }
//        }
//
//        return connection;
//    }
}