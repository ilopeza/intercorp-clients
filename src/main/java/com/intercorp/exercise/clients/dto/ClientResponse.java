package com.intercorp.exercise.clients.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ClientResponse {

    @JsonProperty("first_name")
    @ApiModelProperty(name = "first_name", example = "John", notes = "First name of the client")
    private String firstName;

    @JsonProperty("last_name")
    @ApiModelProperty(name = "last_name", example = "Doe", notes = "Last name of the client")
    private String lastName;

    @JsonProperty("birth_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyy")
    @ApiModelProperty(name = "birth_date", example = "21/03/1990", notes = "Birth date of the client")
    private LocalDate birthDate;

    @JsonProperty("age")
    @ApiModelProperty(name = "age", example = "31", notes = "Age of the client")
    private int age;

    @JsonProperty("death_date")
    @ApiModelProperty(name = "death_date", example = "John", notes = "Predicted death of the client")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyy")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private LocalDate deathDate;
}
