package com.bsbstudylapey.service;

import com.bsbstudylapey.UserNotFoundException;
import com.bsbstudylapey.UserRepository;
import com.bsbstudylapey.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService
{
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public String deleteById(Long id) {
        if(!userRepository.existsById(id)) {
            return "user is not deleted";
        }
        userRepository.deleteById(id);
        return "deleted successfully";
    }
}
