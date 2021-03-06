package com.intercorp.exercise.clients.models;

import com.intercorp.exercise.clients.exceptions.ClientException;
import lombok.Data;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;
import lombok.val;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.util.Objects.isNull;

@Data
@Slf4j
@Entity
@Table(name = "clients")
public class Client {
    @Id
    @GeneratedValue
    private Long id;


    private String firstName;
    private String lastName;
    private Date birthdate;

    public int calculateAge() {
        if (isNull(birthdate)) {
            log.error("Birthdate is null for client {}", this.id);
            throw new ClientException("Birthdate should not be null");
        }

        if (birthdate.after(new Date())) {
            log.error("Birthdate {} of client {} should not be greater that today", birthdate, id);
            throw new ClientException("Birthdate cannot be greater that today");
        }
        val birthYear = birthdate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate().getYear();
        val currentYear = LocalDate.now().getYear();
        return currentYear - birthYear;
    }
}
