package com.bsbstudylapey.controllers;

import com.bsbstudylapey.dto.AddressDto;
import com.bsbstudylapey.models.Address;
import com.bsbstudylapey.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService){
        this.addressService = addressService;
    }

    @GetMapping("address/findAll")
    public ResponseEntity<List<Address>> findAll() {
        return new ResponseEntity<>(addressService.findAll(), HttpStatus.OK);
    }

    @GetMapping("address/findById")
    public ResponseEntity<Address> findById(Long id) {
        return new ResponseEntity<>(addressService.findById(id), HttpStatus.OK);
    }

    @PostMapping("address/saveAddress")
    public ResponseEntity<Address> createAddress(@RequestBody @Valid AddressDto addressDto) {
        return new ResponseEntity<>(addressService.createAddress(addressDto), HttpStatus.OK);
    }

    @PostMapping("address/updateAddress")
    public ResponseEntity<Address> updateUser(@RequestBody @Valid AddressDto addressDto, Long id) {
        return new ResponseEntity<>(addressService.updateAddress(addressDto, id), HttpStatus.OK);
    }

    @DeleteMapping("address/deleteById")
    public ResponseEntity<String> deleteById(Long id) {
        return new ResponseEntity<>(addressService.deleteById(id), HttpStatus.OK);
    }

}
