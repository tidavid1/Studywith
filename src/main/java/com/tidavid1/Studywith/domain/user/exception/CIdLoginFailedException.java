package com.tidavid1.Studywith.domain.user.exception;

public class CIdLoginFailedException extends RuntimeException{
    public CIdLoginFailedException() {
    }

    public CIdLoginFailedException(String message) {
        super(message);
    }

    public CIdLoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
