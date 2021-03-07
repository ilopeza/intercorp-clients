package com.intercorp.exercise.clients.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class KpiClient {
    @JsonProperty("average")
    private double avg;
    @JsonProperty("standard_deviation")
    private double standardDeviation;
}
