package com.altek.intro.exceptions;

public class MissingServletRequestParameterException extends RuntimeException{

    public MissingServletRequestParameterException(String message) {
        super(message);
    }

    public MissingServletRequestParameterException(String message, Throwable cause) {
        super(message, cause);
    }

}
