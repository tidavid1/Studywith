package com.tidavid1.Studywith.domain.teaching.exception;

public class CTeachingNotFoundException extends RuntimeException{
    public CTeachingNotFoundException() {
    }

    public CTeachingNotFoundException(String message) {
        super(message);
    }

    public CTeachingNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
