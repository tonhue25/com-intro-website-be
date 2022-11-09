package com.altek.intro.exceptions;

import com.altek.intro.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({ ResourceNotFoundException.class })
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.NOT_FOUND.toString(), exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ MissingServletRequestParameterException.class })
    public ResponseEntity<Object> handleMissingServletRequestParameterException(MissingServletRequestParameterException exception,
                                                                  WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.toString(), exception.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
