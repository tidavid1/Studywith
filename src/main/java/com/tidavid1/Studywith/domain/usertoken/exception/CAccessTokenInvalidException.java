package com.tidavid1.Studywith.domain.usertoken.exception;

public class CAccessTokenInvalidException extends RuntimeException{
    public CAccessTokenInvalidException() {
    }

    public CAccessTokenInvalidException(String message) {
        super(message);
    }

    public CAccessTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
