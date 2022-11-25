package com.altek.intro.exception;

import java.util.HashMap;

public class BadRequestException extends BaseException {
    private static final long serialVersionUID = 1L;

    private static final int ERROR_STATUS_DEFAULT = 400;

    public BadRequestException(String message, Throwable e) {
        super(ERROR_STATUS_DEFAULT, message, e);
    }

    public BadRequestException(String message) {
        super(ERROR_STATUS_DEFAULT, message);
    }

    public BadRequestException(HashMap<String, String> errMap) {
        super(ERROR_STATUS_DEFAULT, errMap);
    }
}