package com.example.users.exception;

public class UnexeptedResultException extends RuntimeException {
    public UnexeptedResultException() {
    }

    public UnexeptedResultException(String message) {
        super(message);
    }

    public UnexeptedResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexeptedResultException(Throwable cause) {
        super(cause);
    }

    public UnexeptedResultException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
