package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.dto.CreateClientRequest;
import com.intercorp.exercise.clients.exceptions.ClientException;
import com.intercorp.exercise.clients.models.Client;
import com.intercorp.exercise.clients.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(CreateClientRequest request) {
        if (isNull(request)) {
            throw new ClientException("Cannot create Client from empty request");
        }
        log.info("About to save new client with params: {}", request);
        Client client = new Client();
        client.setBirthdate(request.getBirthDate());
        client.setFirstName(request.getFirstName());
        client.setLastName(request.getLastName());

        val savedClient = clientRepository.save(client);
        log.info("New client created:", client);

        return savedClient;
    }
}
