package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.models.Client;

import java.util.Date;

/**
 * Service to calculate the date of death of a client.
 */
public interface ExpectedLifeCalculator {

    /**
     * Determine the death date of a client based on the life expectancy and the birt date.
     *
     * @param client Client to calculate the death date
     * @return death of the client
     */
    Date calculateExpectedDeathDate(Client client);
}
