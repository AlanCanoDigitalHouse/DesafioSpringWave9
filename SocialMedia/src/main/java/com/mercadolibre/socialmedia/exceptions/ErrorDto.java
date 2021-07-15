package com.mercadolibre.socialmedia.exceptions;


import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Data
public class ErrorDto {
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ErrorDto(HttpStatus status, String message, List<String> error) {
        this.status = status;
        this.message = message;
        this.errors = error;
    }
    public ErrorDto(HttpStatus status, String message, String error) {
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }


}