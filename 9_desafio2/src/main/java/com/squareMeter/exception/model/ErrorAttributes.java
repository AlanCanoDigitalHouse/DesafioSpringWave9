package com.squareMeter.exception.model;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorAttributes {
    private final String description;
    private final Map<String, String> fieldErrors;

    public ErrorAttributes(String description) {
        this.description = description;
        fieldErrors = new HashMap<>();
    }

    public void addFieldError(String name, String message) {
        fieldErrors.put(name, message);
    }
}
