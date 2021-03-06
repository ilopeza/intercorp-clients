package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.dto.CreateClientRequest;
import com.intercorp.exercise.clients.models.Client;

public interface ClientService {

    Client createClient(CreateClientRequest request);
}
