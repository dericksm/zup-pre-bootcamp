package com.derick.zupbootcamp.services.exceptions;

public class TokenValidationException extends RuntimeException {

    public TokenValidationException(String message) {
        super(message);
    }
}
