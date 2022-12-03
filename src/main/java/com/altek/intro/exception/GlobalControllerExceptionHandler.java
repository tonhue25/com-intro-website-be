package com.altek.intro.exception;


import com.altek.intro.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler({ResourceNotFoundException.class})
    public ResponseEntity<Object> handleResourceNotFoundException(HttpServletRequest request,
                                                                  ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, request, ex);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({MissingServletRequestParameterException.class})
    public ResponseEntity<Object> handleMissingServletRequestParameterException(HttpServletRequest request,
                                                                                MissingServletRequestParameterException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, request, ex);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
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

    @ExceptionHandler({BadCredentialsException.class})
    public ResponseEntity<Object> handleBadCredentialsException(HttpServletRequest request,
                                                                BadCredentialsException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.UNAUTHORIZED, request, ex);
        return new ResponseEntity<Object>(error, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({SQLException.class})
    public ResponseEntity<Object> handleSQLException(HttpServletRequest request,
                                                     SQLException ex) {
        ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, request, ex);
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> {
            errors.put(e.getField(), e.getDefaultMessage());
        });
        return errors;
    }
}
