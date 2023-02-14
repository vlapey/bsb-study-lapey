package com.bsbstudylapey.controllers;

import com.bsbstudylapey.dto.ClientDto;
import com.bsbstudylapey.models.Client;
import com.bsbstudylapey.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/findById")
    public ResponseEntity<Client> findById(Long id) {
        return new ResponseEntity<>(clientService.findById(id), HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<Client>> findAll() {
        return new ResponseEntity<>(clientService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/saveClient")
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientDto clientDto, Long addressId) {
        return new ResponseEntity<>(clientService.createClient(clientDto, addressId), HttpStatus.OK);
    }

    @PostMapping("/updateClient")
    public ResponseEntity<Client> updateClient(@RequestBody @Valid ClientDto clientDto, Long id, Long addressId) {
        return new ResponseEntity<>(clientService.updateClient(clientDto, id, addressId), HttpStatus.OK);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleterById(Long id) {
        return new ResponseEntity<>(clientService.deleteById(id), HttpStatus.OK);
    }
}
