package com.intercorp.exercise.clients.handlers;

import com.intercorp.exercise.clients.dto.RegularErrorResponse;
import com.intercorp.exercise.clients.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ClientExceptionHandler {

    @ExceptionHandler(ClientException.class)
    public ResponseEntity<RegularErrorResponse> handleClientException(ClientException clientException) {
        val message = clientException.getMessage();
        log.error("Handling ClientException with message: {}", message);
        val regularErrorResponse = RegularErrorResponse.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .message(message)
                .build();
        return new ResponseEntity<>(regularErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = new ArrayList<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        final RegularErrorResponse regularErrorResponse = RegularErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST)
                .errors(errors)
                .message(ex.getMessage())
                .build();
        return ResponseEntity.badRequest().body(regularErrorResponse);
    }
}
