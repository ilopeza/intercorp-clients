package com.intercorp.exercise.clients.controllers;

import com.intercorp.exercise.clients.dto.ClientResponse;
import com.intercorp.exercise.clients.dto.CreateClientRequest;
import com.intercorp.exercise.clients.services.ClientService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/create")
    public ClientResponse createClient(@Valid @RequestBody CreateClientRequest request) {
        log.info("Calling /create with params: {}", request);
        val client = clientService.createClient(request);
        log.info("New client created: {}", client);
        return ClientResponse.builder()
                .age(client.calculateAge())
                .birthDate(client.getBirthdate())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .build();
    }
}
