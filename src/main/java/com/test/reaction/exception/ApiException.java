package com.test.reaction.exception;

public class ApiException extends RuntimeException {
    public ApiException(String message) {
        super(message);
    }
}
