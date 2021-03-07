package com.intercorp.exercise.clients.controllers;

import com.intercorp.exercise.clients.dto.ClientResponse;
import com.intercorp.exercise.clients.dto.CreateClientRequest;
import com.intercorp.exercise.clients.dto.KpiClient;
import com.intercorp.exercise.clients.services.ClientService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.emptyList;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @ApiOperation(value = "Create a new client", response = ClientResponse.class)
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

    @ApiOperation(value = "Find all clients", response = ClientResponse.class)
    @GetMapping("/find-all")
    public List<ClientResponse> findAllClients() {
        log.info("Calling endpoint /find-all");
        val clients = clientService.findAllClients();
        if (isEmpty(clients)) {
            return emptyList();
        }
        log.info("Returning {} clients", clients.size());
        return clients.stream()
                .map(client -> ClientResponse.builder()
                        .firstName(client.getFirstName())
                        .lastName(client.getLastName())
                        .age(client.calculateAge())
                        .birthDate(client.getBirthdate())
                        .deathDate(client.getDeathDate())
                        .build())
                .collect(Collectors.toList());
    }

    @ApiOperation(value = "Find all clients", response = KpiClient.class)
    @GetMapping("/kpideclientes")
    public KpiClient getKpiClient() {
        log.info("Calling endpoint /kpideclientes");
        val kpiForClients = clientService.getKpiForClients();
        log.info("Returning kpi for clients: {}", kpiForClients);
        return kpiForClients;
    }
}
