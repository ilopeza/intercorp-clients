package com.intercorp.exercise.clients.services;

import com.intercorp.exercise.clients.models.Client;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Random;

@Service
public class SimpleExpectedLifeCalculator implements ExpectedLifeCalculator {

    Random random = new Random();
    @Override
    public Date calculateExpectedDeathDate(Client client) {
        //get min and max
        val localBirthDate = client.getBirthdate()
                .toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        val maxYearsToLive = localBirthDate.plusYears(77);
        val maxDaysToLive = localBirthDate.until(maxYearsToLive, ChronoUnit.DAYS);

        //get days from birth since the client should be alive at the moment of creation
        val daysFromBirth = localBirthDate.until(LocalDate.now(), ChronoUnit.DAYS);
        val daysToLive = random.longs(daysFromBirth, maxDaysToLive)
                .findFirst()
                .getAsLong();

        val expectedDeathDate = localBirthDate.plusDays(daysToLive);

        return Date.from(expectedDeathDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
}
