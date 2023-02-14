package com.bsbstudylapey.service;

import com.bsbstudylapey.dto.AddressDto;
import com.bsbstudylapey.mappers.AddressMapper;
import com.bsbstudylapey.models.Address;
import com.bsbstudylapey.repo.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

import static com.bsbstudylapey.Constants.DELETED_SUCCESSFULLY;
import static com.bsbstudylapey.Constants.SUCH_ENTITY_DOES_NOT_EXIST;


@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public List<Address> findAll() {
        return addressRepository.findAll();
    }

    public Address findById(Long id) {
        return addressRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Such address does not exist"));
    }

    public Address createAddress(AddressDto addressDto) {
        return addressRepository.save(AddressMapper.INSTANCE.dtoToAddress(addressDto));
    }

    public Address updateAddress(AddressDto addressDto, Long id) {
        Address oldAddress = addressRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        addressDto.setId(oldAddress.getId());
        return addressRepository.save(AddressMapper.INSTANCE.dtoToAddress(addressDto));
    }

    public String deleteById(Long id) {
        if (!addressRepository.existsById(id)) {
            return SUCH_ENTITY_DOES_NOT_EXIST;
        }
        addressRepository.deleteById(id);
        return DELETED_SUCCESSFULLY;
    }
}
