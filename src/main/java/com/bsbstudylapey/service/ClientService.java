package com.bsbstudylapey.service;

import com.bsbstudylapey.dto.ClientDto;
import com.bsbstudylapey.mappers.ClientMapper;
import com.bsbstudylapey.models.Address;
import com.bsbstudylapey.models.Client;
import com.bsbstudylapey.repo.AddressRepository;
import com.bsbstudylapey.repo.ClientRepository;
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
public class ClientService {

    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public Client findById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Such client does not exist"));
    }

    public Client createClient(ClientDto clientDto, Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(() -> new NoSuchElementException());
        List<Address> addressList = new ArrayList<>();
        addressList.add(address);
        clientDto.setAddressOfClient(addressList);
        return clientRepository.save(ClientMapper.INSTANCE.dtoToClient(clientDto));
    }

    public Client updateClient(ClientDto clientDto, Long id, Long addressId) {
        List<Address> addressList = new ArrayList<>();
        Client oldClient = clientRepository.findById(id).orElseThrow(() -> new NoSuchElementException());
        clientDto.setCreatedAt(oldClient.getCreatedAt());
        clientDto.setId(id);
        if (addressId == null) {
            clientDto.setAddressOfClient(oldClient.getAddressOfClient());
            return clientRepository.save(ClientMapper.INSTANCE.dtoToClient(clientDto));
        }
        addressList.add(addressRepository.findById(addressId).orElseThrow(() -> new NoSuchElementException()));
        clientDto.setAddressOfClient(addressList);
        return clientRepository.save(ClientMapper.INSTANCE.dtoToClient(clientDto));
    }

    public String deleteById(Long id) {
        if (!clientRepository.existsById(id)) {
            return SUCH_ENTITY_DOES_NOT_EXIST;
        }
        clientRepository.deleteById(id);
        return DELETED_SUCCESSFULLY;
    }
}
