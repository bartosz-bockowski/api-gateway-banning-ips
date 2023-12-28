package com.example.carparkinggateway.model;

public class ApiError {

    public ApiError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    private String errorMessage;

    public String getErrorMessage() {
        return errorMessage;
    }
}
