package com.tidavid1.Studywith.domain.teaching.exception;

public class CTeachingEndDateEarlierThenStartDateException extends RuntimeException{
    public CTeachingEndDateEarlierThenStartDateException() {
    }

    public CTeachingEndDateEarlierThenStartDateException(String message) {
        super(message);
    }

    public CTeachingEndDateEarlierThenStartDateException(String message, Throwable cause) {
        super(message, cause);
    }
}
