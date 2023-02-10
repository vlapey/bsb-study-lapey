package com.bsbstudylapey.service;

import com.bsbstudylapey.dto.AddressDto;
import com.bsbstudylapey.models.Address;
import com.bsbstudylapey.models.User;
import com.bsbstudylapey.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static com.bsbstudylapey.Constants.DELETED_SUCCESSFULLY;
import static com.bsbstudylapey.Constants.SUCH_ENTITY_DOES_NOT_EXIST;


@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;

    public List<Address> findAll(){
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Such address does not exist"));
    }

    public Address createAddress(AddressDto addressDto) {
        List<User> userList = new ArrayList<>();
        User user = new User();
        user.setId((long)1);
        userList.add(user);
        Address address = Address.build((long) 0, addressDto.getCountryName(), addressDto.getCityName(), addressDto.getStreet());

        return addressRepository.save(address);
    }

    public Address updateAddress(AddressDto addressDto, Long id) {
        var oldAddress = addressRepository.findById(id).orElseThrow();
        var addressId = oldAddress.getId();

        Address address = Address.build(addressId, addressDto.getCountryName(), addressDto.getCityName(),
                addressDto.getStreet());

        return addressRepository.save(address);
    }

    public String deleteById(Long id) {
        if (!addressRepository.existsById(id)) {
            return SUCH_ENTITY_DOES_NOT_EXIST;
        }
        addressRepository.deleteById(id);
        return DELETED_SUCCESSFULLY;
    }
}
