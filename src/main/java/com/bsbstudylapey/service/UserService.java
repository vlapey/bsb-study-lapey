package com.bsbstudylapey.service;

import com.bsbstudylapey.dto.UserDto;
import com.bsbstudylapey.mappers.UserMapper;
import com.bsbstudylapey.models.Address;
import com.bsbstudylapey.models.User;
import com.bsbstudylapey.repo.AddressRepository;
import com.bsbstudylapey.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static com.bsbstudylapey.Constants.DELETED_SUCCESSFULLY;
import static com.bsbstudylapey.Constants.SUCH_ENTITY_DOES_NOT_EXIST;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AddressRepository addressRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Such user does not exist"));
    }

    public User createUser(UserDto userDto, Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new NoSuchElementException());
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        userDto.setAddressOfUser(addressList);
        return userRepository.save(UserMapper.INSTANCE.dtoToUser(userDto));
    }

    public User updateUser(UserDto userDto, Long id, Long addressId) {
        List<Address> addressList = new ArrayList<>();
        User userBeforeUpdate = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        userDto.setCreatedAt(userBeforeUpdate.getCreatedAt());
        userDto.setId(id);
        if (addressId == null) {
            userDto.setAddressOfUser(userBeforeUpdate.getAddressOfUser());
            return userRepository.save(UserMapper.INSTANCE.dtoToUser(userDto));
        }
        addressList.add(addressRepository.findById(addressId).orElseThrow(() -> new NoSuchElementException()));
        userDto.setAddressOfUser(addressList);
        return userRepository.save(UserMapper.INSTANCE.dtoToUser(userDto));
    }

    public String deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            return SUCH_ENTITY_DOES_NOT_EXIST;
        }
        userRepository.deleteById(id);
        return DELETED_SUCCESSFULLY;
    }
}
