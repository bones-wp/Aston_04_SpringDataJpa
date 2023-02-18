package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.dao.UserDAO;
import org.example.entity.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class TestController {
    private final UserDAO userDAO;

    @GetMapping
    public List<User> getUsers() {
        System.out.println(userDAO);
        return userDAO.getUsers();
    }


//    @PostMapping(value = "/addUser")
//    public ResponseEntity<?> createUser(@RequestParam("name") String name) throws SQLException {
//        if (connection.isPresent()) {
//            stmt = connection.get().createStatement();
//        }
//
//        //User user = new User(name);
//        //userRepo.save(user);
//
//        String psql = "INSERT INTO USERS (name) VALUES ('" + name + "')";
//        stmt.executeUpdate(psql);
//
//        return new ResponseEntity<>(HttpStatus.CREATED);
//
//    }

}

