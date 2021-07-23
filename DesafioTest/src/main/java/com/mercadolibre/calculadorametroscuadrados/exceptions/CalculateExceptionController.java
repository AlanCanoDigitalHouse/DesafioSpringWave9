package com.mercadolibre.calculadorametroscuadrados.exceptions;

import com.mercadolibre.calculadorametroscuadrados.dto.ErrorDTO;
import lombok.Getter;

import java.util.Map;

@Getter
public class CalculateExceptionController extends RuntimeException {
    private ErrorDTO errorDTO;

    public CalculateExceptionController(Integer status, String error, Map<String, String> message) {
        this.errorDTO = new ErrorDTO(status, error, message);
    }

    public CalculateExceptionController(ErrorDTO errorDTO) {
        this.errorDTO = errorDTO;
    }
}
