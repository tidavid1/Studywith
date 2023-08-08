package com.tidavid1.Studywith.domain.usertoken.exception;

public class CAccessTokenExpiredException extends RuntimeException{
    public CAccessTokenExpiredException() {
    }

    public CAccessTokenExpiredException(String message) {
        super(message);
    }

    public CAccessTokenExpiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
