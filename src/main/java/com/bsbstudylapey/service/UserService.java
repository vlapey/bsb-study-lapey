package com.bsbstudylapey.service;

import com.bsbstudylapey.models.Address;
import com.bsbstudylapey.repo.UserRepository;
import com.bsbstudylapey.dto.UserDto;
import com.bsbstudylapey.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.bsbstudylapey.Constants.DELETED_SUCCESSFULLY;
import static com.bsbstudylapey.Constants.SUCH_ENTITY_DOES_NOT_EXIST;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
         return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Such user does not exist"));
    }

    public User createUser(UserDto userDto) {
        var createdDate = Date.valueOf(LocalDate.now());
        var updatedDate = createdDate;

        List<Address> addressList = new ArrayList<>();
        Address address = new Address();
        address.setId((long)1);
        addressList.add(address);
        User user = User.build((long) 0, userDto.getFirstName(), userDto.getLastName(),
                userDto.getPhoneNumber(), userDto.getEmail(), createdDate, updatedDate, addressList);

        return userRepository.save(user);
    }

    public User updateUser(UserDto userDto, Long id) {
        var oldUser = userRepository.findById(id).orElseThrow();
        var userId = oldUser.getId();
        var createdDate = oldUser.getCreatedAt();
        var updatedDate = Date.valueOf(LocalDate.now());

        List<Address> addressList = new ArrayList<>();
        Address address = new Address();
        address.setId((long)1);
        addressList.add(address);

        User user = User.build(userId, userDto.getFirstName(), userDto.getLastName(),
                userDto.getPhoneNumber(), userDto.getEmail(), createdDate, updatedDate, addressList);

        return userRepository.save(user);
    }

    public String deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            return SUCH_ENTITY_DOES_NOT_EXIST;
        }
        userRepository.deleteById(id);
        return DELETED_SUCCESSFULLY;
    }
}
