package com.bsbstudylapey.service;

import com.bsbstudylapey.UserRepository;
import com.bsbstudylapey.dto.UserRequest;
import com.bsbstudylapey.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserService
{
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Such user does not exists"));
    }

    public User createUser(UserRequest userRequest) {
        var createdDate = Date.valueOf(LocalDate.now());
        var updatedDate = createdDate;

        User user = User.build((long)0, userRequest.getFirstName(), userRequest.getLastName(),
                userRequest.getPhoneNumber(), userRequest.getEmail(), createdDate, updatedDate);

        if(user.getCreatedAt() == null){
            user.setCreatedAt(Date.valueOf(LocalDate.now()));
        }
        if(user.getUpdatedAt() == null){
            user.setUpdatedAt(Date.valueOf(LocalDate.now()));
        }
        return userRepository.save(user);
    }

    public User updateUser(UserRequest userRequest, Long id){
        var oldUser = userRepository.findById(id).orElseThrow();
        var userId = oldUser.getId();
        var createdDate = oldUser.getCreatedAt();
        var updatedDate = Date.valueOf(LocalDate.now());

        User user = User.build(userId, userRequest.getFirstName(), userRequest.getLastName(),
                userRequest.getPhoneNumber(), userRequest.getEmail(), createdDate, updatedDate);

        return userRepository.save(user);
    }

    public String deleteById(Long id) {
        if(!userRepository.existsById(id)) {
            return "such user does not exists";
        }
        userRepository.deleteById(id);
        return "deleted successfully";
    }
}
