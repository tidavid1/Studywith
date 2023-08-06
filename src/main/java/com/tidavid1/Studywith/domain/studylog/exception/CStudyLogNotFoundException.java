package com.tidavid1.Studywith.domain.studylog.exception;

public class CStudyLogNotFoundException extends RuntimeException{
    public CStudyLogNotFoundException() {
    }

    public CStudyLogNotFoundException(String message) {
        super(message);
    }

    public CStudyLogNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
