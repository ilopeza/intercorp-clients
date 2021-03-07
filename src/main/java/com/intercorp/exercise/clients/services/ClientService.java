package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.dto.CreateClientRequest;
import com.intercorp.exercise.clients.models.Client;

import java.util.List;

/**
 * Service to operate with client object.
 */
public interface ClientService {

    /**
     * Creates a new client
     *
     * @param request data to create a client
     * @return the saved client
     */
    Client createClient(CreateClientRequest request);

    /**
     * Find all the clients stored
     *
     * @return List of all the clients
     */
    List<Client> findAllClients();
}
