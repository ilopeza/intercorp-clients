package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.exceptions.ExpectedLifeCalcException;
import com.intercorp.exercise.clients.models.Client;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;

import static java.util.Objects.isNull;

@Slf4j
@Service
public class SimpleExpectedLifeCalculator implements ExpectedLifeCalculator {

    Random random = new Random();

    @Override
    public LocalDate calculateExpectedDeathDate(Client client) {
        if (isNull(client)) {
            log.error("Cannot predict death date for a null client");
            throw new ExpectedLifeCalcException("Cannot predict death date for a null client");
        }

        if (isNull(client.getBirthdate())) {
            log.error("Cannot predict death date for a client {} with null birthdate", client.getId());
            throw new ExpectedLifeCalcException("Cannot get expected death date for a client with null birthdate");
        }

        //get min and max
        val localBirthDate = client.getBirthdate();
        val maxYearsToLive = localBirthDate.plusYears(77);
        val maxDaysToLive = localBirthDate.until(maxYearsToLive, ChronoUnit.DAYS);

        //get days from birth since the client should be alive at the moment of creation
        val daysFromBirth = localBirthDate.until(LocalDate.now(), ChronoUnit.DAYS);
        val daysToLive = random.longs(daysFromBirth, maxDaysToLive)
                .findFirst()
                .getAsLong();

        return localBirthDate.plusDays(daysToLive);
    }
}
