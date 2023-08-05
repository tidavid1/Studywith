package com.tidavid1.Studywith.domain.teaching.exception;

public class CTeachingAlreadyExistException extends RuntimeException{
    public CTeachingAlreadyExistException() {
    }

    public CTeachingAlreadyExistException(String message) {
        super(message);
    }

    public CTeachingAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
