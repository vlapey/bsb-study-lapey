package com.bsbstudylapey.controllers;

import com.bsbstudylapey.dto.UserDto;
import com.bsbstudylapey.models.User;
import com.bsbstudylapey.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("user/findById")
    public ResponseEntity<User> findById(Long id) {
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    @GetMapping("user/findAll")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @PostMapping("user/saveUser")
    public ResponseEntity<User> createUser(@RequestBody @Valid UserDto userDto, Long addressId) {
        return new ResponseEntity<>(userService.createUser(userDto, addressId), HttpStatus.OK);
    }

    @PostMapping("user/updateUser")
    public ResponseEntity<User> updateUser(@RequestBody @Valid UserDto userDto, Long id, Long addressId) {
        return new ResponseEntity<>(userService.updateUser(userDto, id, addressId), HttpStatus.OK);
    }

    @DeleteMapping("user/deleteById")
    public ResponseEntity<String> deleterById(Long id) {
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }
}
