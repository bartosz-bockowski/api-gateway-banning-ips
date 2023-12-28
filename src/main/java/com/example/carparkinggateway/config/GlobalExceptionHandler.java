package com.example.carparkinggateway.config;

import com.example.carparkinggateway.exception.AccessDeniedException;
import com.example.carparkinggateway.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiError> handleAccessDeniedException(AccessDeniedException e) {
        return new ResponseEntity<>(new ApiError(e.getMessage()), HttpStatus.FORBIDDEN);
    }

}
