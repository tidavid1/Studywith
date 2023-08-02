package com.tidavid1.Studywith.domain.user.exception;

public class CIdSignupFailedException extends RuntimeException{
    public CIdSignupFailedException() {
    }

    public CIdSignupFailedException(String message) {
        super(message);
    }

    public CIdSignupFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
