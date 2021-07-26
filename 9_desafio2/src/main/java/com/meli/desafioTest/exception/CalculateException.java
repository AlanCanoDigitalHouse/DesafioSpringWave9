package com.meli.desafioTest.exception;


import com.meli.desafioTest.Dtos.ErrorDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CalculateException extends RuntimeException {

    private final ErrorDTO error;
    private final HttpStatus status;

    public CalculateException(String message, HttpStatus status) {
        this.error = new ErrorDTO(this.getClass().getSimpleName(), message);
        this.status = status;
    }

}