package com.socialMeli.exception.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ErrorAttributes {
    private String description;
    private Map<String, String> fieldErrors;

    public ErrorAttributes(String description) {
        this.description = description;
        fieldErrors = new HashMap<>();
    }

    public void addFieldError(String name, String message) {
        fieldErrors.put(name, message);
    }
}
