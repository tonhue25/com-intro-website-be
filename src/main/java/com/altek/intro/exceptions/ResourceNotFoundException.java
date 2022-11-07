package com.altek.intro.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private static final int ERROR_STATUS_DEFAULT = 404;

//    public ResourceNotFoundException(String message, Throwable e) {
//        super(ERROR_STATUS_DEFAULT, message, e);
//    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}