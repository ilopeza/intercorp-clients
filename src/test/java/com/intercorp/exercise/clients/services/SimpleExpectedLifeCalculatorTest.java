package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.exceptions.ExpectedLifeCalcException;
import com.intercorp.exercise.clients.models.Client;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class SimpleExpectedLifeCalculatorTest {

    @Mock
    Client client;

    SimpleExpectedLifeCalculator simpleExpectedLifeCalculator;

    @BeforeEach
    void setupMocks() {
        MockitoAnnotations.openMocks(this);
        simpleExpectedLifeCalculator = new SimpleExpectedLifeCalculator();
    }

    @Test
    void calculateExpectedDeathDate_with_null_client_should_throw_exception() {
        assertThrows(ExpectedLifeCalcException.class, () -> simpleExpectedLifeCalculator.calculateExpectedDeathDate(null));
    }

    @Test
    void calculateExpectedDeathDate_with_null_birthdate_should_throw_exception() {
        when(client.getBirthdate()).thenReturn(null);
        assertThrows(ExpectedLifeCalcException.class, () -> simpleExpectedLifeCalculator.calculateExpectedDeathDate(null));
    }

    @Test
    void calculateExpectedDeathDate_should_return_random_value() {
        final LocalDate now = LocalDate.now();
        LocalDate date = now.minusYears(15);
        when(client.getBirthdate()).thenReturn(date);

        LocalDate expectedDeath = simpleExpectedLifeCalculator.calculateExpectedDeathDate(client);

        assertNotNull(expectedDeath);
        assertTrue(expectedDeath.isAfter(date));
        assertTrue(expectedDeath.isEqual(now) || expectedDeath.isAfter(now));
    }
}