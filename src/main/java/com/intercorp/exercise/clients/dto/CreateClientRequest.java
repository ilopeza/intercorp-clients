package com.intercorp.exercise.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;
import javax.validation.constraints.NotBlank;

@Data
public class CreateClientRequest {

    @NotBlank
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank
    @JsonProperty("last_name")
    private String lastName;

    @NotBlank
    @JsonProperty("birth_date")
    private Date birthDate;
}
