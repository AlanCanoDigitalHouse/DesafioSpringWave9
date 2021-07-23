package com.mercadolibre.calculadorametroscuadrados.exceptions;

import com.mercadolibre.calculadorametroscuadrados.dto.ErrorDTO;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class NotFoundLocation extends Exception {

    private ErrorDTO error;
    private HttpStatus status;

    public NotFoundLocation(String error, HttpStatus status) {
        this.error = new ErrorDTO(this.getClass().getSimpleName(), error);
        this.status = status;
    }
}
