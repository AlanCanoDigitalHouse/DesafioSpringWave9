package com.mercadolibre.calculadorametroscuadrados.exception;

import com.mercadolibre.calculadorametroscuadrados.dto.Response.ErrorDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class CalculateHouseException extends RuntimeException {

    private final ErrorDTO error;
    private final HttpStatus status;

    public CalculateHouseException(String message, HttpStatus status) {
        this.error = new ErrorDTO(this.getClass().getSimpleName(), message);
        this.status = status;
    }

}