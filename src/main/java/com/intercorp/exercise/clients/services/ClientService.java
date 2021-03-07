package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.dto.CreateClientRequest;
import com.intercorp.exercise.clients.models.Client;

import java.util.List;

public interface ClientService {

    Client createClient(CreateClientRequest request);

    List<Client> findAllClients();
}
