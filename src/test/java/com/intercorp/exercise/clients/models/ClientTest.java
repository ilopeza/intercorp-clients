package com.intercorp.exercise.clients.models;

import com.intercorp.exercise.clients.exceptions.ClientException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    Client client;

    @BeforeEach
    void setupMocks() {
        client = new Client();
    }

    @Test
    void calculateAge_with_null_birth_date_should_throws_exception() {
        client.setBirthdate(null);
        assertThrows(ClientException.class, () -> client.calculateAge());
    }

    @Test
    void calculateAge_with_birth_date_after_today_should_throws_exception() {
        client.setBirthdate(LocalDate.now().plusYears(1));
        assertThrows(ClientException.class, () -> client.calculateAge());
    }

    @Test
    void calculateAge_with_valid_birth_date_returns_age() {
        client.setBirthdate(LocalDate.now().minusYears(10));
        int age = client.calculateAge();
        assertEquals(10, age);
    }
}