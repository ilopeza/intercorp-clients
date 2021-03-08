package com.intercorp.exercise.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class KpiClient {
    @JsonProperty("age_average")
    @ApiModelProperty(name = "age_average", example = "23,78", notes = "Age average for all clients")
    private double ageAvg;

    @JsonProperty("standard_deviation")
    @ApiModelProperty(name = "standard_deviation", example = "3,672", notes = "Standard deviation of age for all clients")
    private double standardDeviation;
}
