package org.example.controller;

import org.example.configurations.JdbcConnection;
import org.example.entity.User;
import org.example.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

@RestController
public class TestController {
    private final Optional<Connection> connection;
    private Statement stmt;
    private final UserRepo userRepo;

    @Autowired
    public TestController(Optional<Connection> connection, UserRepo userRepo) {
        this.userRepo = userRepo;
        this.connection = JdbcConnection.getConnection();
    }


    @PostMapping(value = "/addUser")
    public ResponseEntity<?> createUser (@RequestParam("name") String name) throws SQLException {
        if (connection.isPresent()) {
            stmt = connection.get().createStatement();
        }

        User user = new User(name);
        userRepo.save(user);

//        String psql = "INSERT INTO USERS (name) VALUES ('" + name + "')";
//        stmt.executeUpdate(psql);

        return new ResponseEntity<>(HttpStatus.CREATED);

    }

}

