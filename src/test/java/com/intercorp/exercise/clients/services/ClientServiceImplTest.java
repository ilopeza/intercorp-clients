package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.dto.KpiClient;
import com.intercorp.exercise.clients.exceptions.ClientException;
import com.intercorp.exercise.clients.models.Client;
import com.intercorp.exercise.clients.repositories.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class ClientServiceImplTest {

    ClientServiceImpl clientService;
    @Mock
    ClientRepository clientRepository;
    @Mock
    ExpectedLifeCalculator expectedLifeCalculator;

    @BeforeEach
    void initMocks() {
        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImpl(clientRepository, expectedLifeCalculator);
    }


    @Test
    void createClient_should_throw_exception_with_null_parameter() {
        assertThrows(ClientException.class, () -> clientService.createClient(null));
    }

    @Test
    void getKpiForClients_with_empty_list() {
        when(clientRepository.findAll()).thenReturn(emptyList());
        KpiClient kpiForClients = clientService.getKpiForClients();
        assertNotNull(kpiForClients);
        assertEquals(0, kpiForClients.getAgeAvg());
        assertEquals(0, kpiForClients.getStandardDeviation());
    }

    @Test
    void getKpiForClients_with_non_empty_list() {
        LocalDate birth = LocalDate.now().plusYears(-15);
        Client client1 = new Client();
        client1.setBirthdate(birth);

        LocalDate birth2 = LocalDate.now().plusYears(-10);
        Client client2 = new Client();
        client2.setBirthdate(birth2);

        LocalDate birth3 = LocalDate.now().plusYears(-20);
        Client client3 = new Client();
        client3.setBirthdate(birth3);

        List<Client> clients = new ArrayList<>();
        clients.add(client1);
        clients.add(client2);
        clients.add(client3);

        when(clientRepository.findAll()).thenReturn(clients);

        KpiClient kpiForClients = clientService.getKpiForClients();
        assertNotNull(kpiForClients);
        assertEquals(15, kpiForClients.getAgeAvg());
        assertEquals(4.08248290463863, kpiForClients.getStandardDeviation());
    }
}