package com.desafio.spring.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class Error {
    private HttpStatus status;
    private String message;
}
