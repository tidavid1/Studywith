package com.tidavid1.Studywith.domain.usertoken.exception;

public class CRefreshTokenInvalidException extends RuntimeException{
    public CRefreshTokenInvalidException() {
    }

    public CRefreshTokenInvalidException(String message) {
        super(message);
    }

    public CRefreshTokenInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
