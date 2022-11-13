package com.altek.intro.exceptions;


import com.altek.intro.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request,
                                                                  ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, request, ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<Object> handleIllegalArgumentException(HttpServletRequest request,
                                                                 IllegalArgumentException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, request, ex);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    public ResponseEntity<Object> handleMethodNotSupportedException(HttpServletRequest request,
                                                                    HttpRequestMethodNotSupportedException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.METHOD_NOT_ALLOWED, request, ex);
        return new ResponseEntity<Object>(error, HttpStatus.METHOD_NOT_ALLOWED);
    }
}
