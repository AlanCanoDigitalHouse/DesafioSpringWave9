package com.example.desafio2.exceptions;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class ApiExceptionHandlerTest {

    @Test
    void handleAnyError() {
        ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();
        String message = "Bad request!";
        assertEquals(apiExceptionHandler.handleAnyError(new BadRequestException(message)),
                new ErrorDTO(HttpStatus.BAD_REQUEST.value(),
                        message));
    }
}