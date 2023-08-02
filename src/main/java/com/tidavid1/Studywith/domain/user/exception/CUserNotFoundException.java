package com.tidavid1.Studywith.domain.user.exception;

public class CUserNotFoundException extends RuntimeException{
    public CUserNotFoundException() {
    }

    public CUserNotFoundException(String message) {
        super(message);
    }

    public CUserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
