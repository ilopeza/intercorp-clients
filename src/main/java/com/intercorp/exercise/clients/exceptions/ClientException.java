package com.intercorp.exercise.clients.exceptions;

/**
 * Custom excpetion to handle clients errors.
 */
public class ClientException extends RuntimeException {

    public ClientException(String message) {
        super(message);
    }
}
