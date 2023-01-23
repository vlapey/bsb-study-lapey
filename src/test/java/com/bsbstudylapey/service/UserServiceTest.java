package com.bsbstudylapey.service;

import com.bsbstudylapey.UserRepository;
import com.bsbstudylapey.models.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    @Order(1)
    public void createUserTest(){
        User user = new User();
        user.setFirstName("vlad");
        user.setLastName("lapey");
        user.setPhoneNumber("+375291248846");
        user.setEmail("dadaya10@gmail.com");
        userRepository.save(user);
        Assertions.assertThat(user.getId() > 0);
    }

    @Test
    @Order(2)
    public void getUsersIdTest(){
        User user = userRepository.findById(1L).get();
        Assertions.assertThat(user.getId() == 1L);
    }

    @Test
    @Order(3)
    public void getListOfUsersTest() {
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.size() >= 0);
    }

    @Test
    @Order(4)
    public void updateUserTest() {
        User user = userRepository.findById(1L).get();
        user.setEmail("dadaya10@gmail.com");
        var savedUser = userRepository.save(user);
        Assertions.assertThat(savedUser.getEmail() == "dadaya10@gmail.com");
    }

    @Test
    @Order(5)
    public void deleteUserTest() {
        User user = userRepository.findById(1L).get();
        userRepository.delete(user);
        Optional<User> optionalUser = userRepository.findByEmail("dadaya10@gmail.com");
        User userTest = null;
        if(optionalUser.isPresent()){
            userTest = optionalUser.get();
        }
        Assertions.assertThat(userTest != null);
    }
}
