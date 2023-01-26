package com.bsbstudylapey.service;

import com.bsbstudylapey.UserRepository;
import com.bsbstudylapey.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = false)
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void createUserTest() {
        User user = new User();
        user.setFirstName("vlad");
        user.setLastName("lapey");
        user.setPhoneNumber("+375291248846");
        user.setEmail("dadaya10@gmail.com");
        user.setCreatedAt(Date.valueOf(LocalDate.now()));
        user.setUpdatedAt(Date.valueOf(LocalDate.now()));
        userRepository.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getUsersIdTest() {
        var userList = userRepository.findAll();
        var user = userList.get(userList.size() - 1);
        Assertions.assertThat(user.getId()).isInstanceOf(Long.class);
    }

    @Test
    @Order(3)
    public void getListOfUsersTest() {
        List<User> users = userRepository.findAll();

        Assertions.assertThat(users.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateUserTest() {
        var userList = userRepository.findAll();
        var user = userList.get(userList.size() - 1);
        user.setEmail("dadaya10@gmail.com");
        var savedUser = userRepository.save(user);
        Assertions.assertThat(savedUser.getEmail()).isEqualTo("dadaya10@gmail.com");
    }

    @Test
    @Order(5)
    public void deleteUserTest() {
        var userList = userRepository.findAll();
        var user = userList.get(userList.size() - 1);
        var userId = user.getId();
        userRepository.deleteById(userId);
        var optionalUser = userRepository.findById(userId);
        User userTest = null;

        if (optionalUser.isPresent()) {
            userTest = optionalUser.get();
        }

        Assertions.assertThat(userTest).isNull();
    }
}
