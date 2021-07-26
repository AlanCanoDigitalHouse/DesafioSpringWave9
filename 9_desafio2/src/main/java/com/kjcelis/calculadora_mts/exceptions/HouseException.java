package com.kjcelis.calculadora_mts.exceptions;

import com.kjcelis.calculadora_mts.dto.ErrorDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class HouseException extends RuntimeException {


    private final ErrorDTO error;
    private final HttpStatus status;

    public HouseException(String message, HttpStatus status) {
        this.error = new ErrorDTO(this.getClass().getSimpleName(), message);
        this.status = status;
    }
}
