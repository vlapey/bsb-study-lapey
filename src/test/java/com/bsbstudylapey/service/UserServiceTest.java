package com.bsbstudylapey.service;

import com.bsbstudylapey.UserRepository;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


class UserServiceTest {
    private final UserRepository userRepository;

    UserServiceTest(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Test
    void findById() {
        List<Long> list = new ArrayList<>();
        for (var i = (long) 0; i < 3; i++) {
            list.add(i);
            for (var digit :
                    list) {
                userRepository.findById(digit);
            }
        }
    }
}