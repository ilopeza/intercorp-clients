package com.intercorp.exercise.clients.models;

import com.intercorp.exercise.clients.exceptions.ClientException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

import static java.util.Objects.isNull;

@Data
@Slf4j
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private LocalDate deathDate;

    public int calculateAge() {
        if (isNull(birthdate)) {
            log.error("Birthdate is null for client {}", id);
            throw new ClientException("Birthdate should not be null");
        }

        if (birthdate.isAfter(LocalDate.now())) {
            log.error("Birthdate {} of client {} should not be greater that today", birthdate, id);
            throw new ClientException("Birthdate cannot be greater that today");
        }
        val birthYear = birthdate.getYear();
        val currentYear = LocalDate.now().getYear();
        return currentYear - birthYear;
    }
}
