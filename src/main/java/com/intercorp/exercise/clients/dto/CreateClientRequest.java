package com.intercorp.exercise.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class CreateClientRequest {

    @NotBlank(message = "First name can not be null or empty")
    @ApiModelProperty(name = "first_name", example = "John", notes = "First name of the client")
    @JsonProperty("first_name")
    private String firstName;

    @NotBlank(message = "Last name can not be null or empty")
    @ApiModelProperty(name = "last_name", example = "Doe", notes = "Last name of the client")
    @JsonProperty("last_name")
    private String lastName;

    @NotNull(message = "Birth date can not be null")
    @ApiModelProperty(name = "birth_date", example = "21/03/1990", notes = "Birth date of the client")
    @JsonProperty("birth_date")
    private LocalDate birthDate;
}
