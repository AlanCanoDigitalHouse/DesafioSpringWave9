package com.tucasitatasaciones.DTOs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@AllArgsConstructor
@Getter
public class ErrorDTO {
    private final HttpStatus status;
    private final String error;
    private final Map<String, String> message;
}