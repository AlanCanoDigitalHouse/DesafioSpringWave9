package com.socialmeli.exceptions;

import lombok.Data;

import java.util.Map;

@Data
public class ApiException extends RuntimeException {
    private ErrorMessage errorMessage;

    public ApiException(Integer status, String error, Map<String, String> message) {
        this.errorMessage = new ErrorMessage(status, error, message);
    }
}
