package com.bsbstudylapey.controllers;

import com.bsbstudylapey.models.User;
import com.bsbstudylapey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/findById")
    public ResponseEntity<User> findById(Long id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("user/findAll")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("user/saveUser")
    public ResponseEntity<User> saveUser(User user) {
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    @PostMapping("user/deleteUserById")
    public ResponseEntity<String> deleteUserById(Long id) {
        return new ResponseEntity<>(userService.deleteUserById(id), HttpStatus.OK);
    }
}
