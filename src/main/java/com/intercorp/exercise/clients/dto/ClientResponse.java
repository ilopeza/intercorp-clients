package com.intercorp.exercise.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ClientResponse {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("life_expectancy")
    private int lifeExpectancy;
    @JsonProperty("birth_date")
    private Date birthDate;
    @JsonProperty("age")
    private int age;
}
