package com.api.firstspringchallenge.utils;

import com.api.firstspringchallenge.dtos.response.ErrorResponseDTO;
import org.springframework.http.HttpStatus;

import java.time.Instant;

public class ErrorMessageUtils {
    public static ErrorResponseDTO createErrorMessage(String message, HttpStatus status){
        return new ErrorResponseDTO(Instant.now(),status.value(),status.getReasonPhrase(),message);
    }
}
