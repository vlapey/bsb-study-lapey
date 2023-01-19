package com.bsbstudylapey.controllers;

import com.bsbstudylapey.models.User;
import com.bsbstudylapey.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller
public class UserController {
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("users/findAll")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }
}
