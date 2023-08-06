package com.tidavid1.Studywith.domain.studylog.exception;

public class CStudyLogAlreadyExistException extends RuntimeException{
    public CStudyLogAlreadyExistException() {
    }

    public CStudyLogAlreadyExistException(String message) {
        super(message);
    }

    public CStudyLogAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
