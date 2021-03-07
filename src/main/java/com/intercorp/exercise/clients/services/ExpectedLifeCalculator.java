package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.models.Client;

import java.util.Date;

public interface ExpectedLifeCalculator {
    Date calculateExpectedDeathDate(Client client);
}
