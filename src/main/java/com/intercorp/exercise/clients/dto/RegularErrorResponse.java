package com.intercorp.exercise.clients.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Object to display a custom response most common exceptions are thrown.
 */
@Data
@Builder
public class RegularErrorResponse {
    private HttpStatus status;
    private String message;
    private List<String> errors;
}
