package com.meli.joescaos.desafiotesting.exceptions;

import java.util.Map;

public class ErrorDtoWithFieldErrors extends ErrorDto {
    Map<String, String> errors;

    public ErrorDtoWithFieldErrors(int status, String message, Map<String, String> errors) {
        super(status, message);
        this.errors = errors;
    }
}
