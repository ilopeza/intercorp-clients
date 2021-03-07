package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.dto.CreateClientRequest;
import com.intercorp.exercise.clients.dto.KpiClient;
import com.intercorp.exercise.clients.exceptions.ClientException;
import com.intercorp.exercise.clients.models.Client;
import com.intercorp.exercise.clients.repositories.ClientRepository;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Objects.isNull;
import static org.springframework.util.CollectionUtils.isEmpty;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ExpectedLifeCalculator expectedLifeCalculator;

    public ClientServiceImpl(ClientRepository clientRepository,
                             ExpectedLifeCalculator expectedLifeCalculator) {
        this.clientRepository = clientRepository;
        this.expectedLifeCalculator = expectedLifeCalculator;
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
        //calculate the death date and save it
        val expectedDeath = expectedLifeCalculator.calculateExpectedDeathDate(client);
        client.setDeathDate(expectedDeath);
        log.info("Saving client with data: {}", client);
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public KpiClient getKpiForClients() {
        val clients = clientRepository.findAll();
        KpiClient kpi = new KpiClient();
        if (isEmpty(clients)) {
            return kpi;
        }
        val doubleSummaryStatistics = clients.stream()
                .mapToDouble(Client::calculateAge)
                .summaryStatistics();
        val avg = doubleSummaryStatistics.getAverage();

        double sd = getStandardDeviation(clients, doubleSummaryStatistics, avg);
        kpi.setAvg(avg);
        kpi.setStandardDeviation(sd);
        return kpi;
    }

    private double getStandardDeviation(List<Client> clients, java.util.DoubleSummaryStatistics doubleSummaryStatistics, double avg) {
        val sum = clients.stream()
                .mapToDouble(Client::calculateAge)
                .map(age -> age - avg)
                .map(operand -> Math.pow(operand, 2))
                .sum();

        return Math.pow(sum / doubleSummaryStatistics.getCount(), 0.5);
    }
}
