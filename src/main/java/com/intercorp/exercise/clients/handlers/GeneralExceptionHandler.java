package com.intercorp.exercise.clients.handlers;

import com.intercorp.exercise.clients.dto.RegularErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static java.util.Collections.singletonList;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleGeneralException(Exception exception, WebRequest request) {
        String message = exception.getLocalizedMessage();

        final RegularErrorResponse regularErrorResponse = RegularErrorResponse.builder()
                .message(message)
                .errors(singletonList("Unexpected error occured"))
                .build();
        return new ResponseEntity(regularErrorResponse, INTERNAL_SERVER_ERROR);
    }
}
