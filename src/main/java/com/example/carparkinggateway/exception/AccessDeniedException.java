package com.example.carparkinggateway.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class AccessDeniedException extends RuntimeException {

    public AccessDeniedException(String message) {
        super(message);
    }

}
