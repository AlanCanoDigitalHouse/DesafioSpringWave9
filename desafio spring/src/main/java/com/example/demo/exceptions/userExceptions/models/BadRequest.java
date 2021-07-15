package com.example.demo.exceptions.userExceptions.models;

public class BadRequest {
    private final Integer statusCode;
    private final String message;

    public BadRequest(Integer statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }
}
