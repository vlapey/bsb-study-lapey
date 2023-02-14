package com.bsbstudylapey.service;

import com.bsbstudylapey.models.Client;
import com.bsbstudylapey.repo.ClientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Rollback(value = false)
public class ClientServiceTest {
    @Autowired
    private ClientRepository clientRepository;

    @Test
    @Order(1)
    public void createUserTest() {
        Client client = new Client();
        client.setFirstName("vlad");
        client.setLastName("lapey");
        client.setPhoneNumber("+375291248846");
        client.setEmail("dadaya10@gmail.com");
        clientRepository.save(client);
        Assertions.assertThat(client.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getUsersIdTest() {
        List<Client> clientList = clientRepository.findAll();
        Client client = clientList.get(clientList.size() - 1);
        Assertions.assertThat(client.getId()).isInstanceOf(Long.class);
    }

    @Test
    @Order(3)
    public void getListOfUsersTest() {
        List<Client> clients = clientRepository.findAll();

        Assertions.assertThat(clients.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    public void updateUserTest() {
        List<Client> clientList = clientRepository.findAll();
        Client client = clientList.get(clientList.size() - 1);
        client.setEmail("dadaya10@gmail.com");
        Client savedClient = clientRepository.save(client);
        Assertions.assertThat(savedClient.getEmail()).isEqualTo("dadaya10@gmail.com");
    }

    @Test
    @Order(5)
    public void deleteUserTest() {
        List<Client> clientList = clientRepository.findAll();
        Client client = clientList.get(clientList.size() - 1);
        Long userId = client.getId();
        clientRepository.deleteById(userId);
        Optional<Client> optionalUser = clientRepository.findById(userId);
        Client clientTest = null;

        if (optionalUser.isPresent()) {
            clientTest = optionalUser.get();
        }

        Assertions.assertThat(clientTest).isNull();
    }
}
