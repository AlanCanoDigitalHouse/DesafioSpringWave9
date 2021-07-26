package com.meli.joescaos.desafiotesting.exceptions;

import lombok.Data;

import java.util.Map;

@Data
public class ErrorDtoWithFieldErrors extends ErrorDto {
    Map<String, String> errors;

    public ErrorDtoWithFieldErrors(int status, String message, Map<String, String> errors) {
        super(status, message);
        this.errors = errors;
    }
}
