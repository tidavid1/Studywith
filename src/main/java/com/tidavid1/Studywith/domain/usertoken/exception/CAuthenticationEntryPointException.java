package com.tidavid1.Studywith.domain.usertoken.exception;

public class CAuthenticationEntryPointException extends RuntimeException{
    public CAuthenticationEntryPointException() {
    }

    public CAuthenticationEntryPointException(String message) {
        super(message);
    }

    public CAuthenticationEntryPointException(String message, Throwable cause) {
        super(message, cause);
    }
}
