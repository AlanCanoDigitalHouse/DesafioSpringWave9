package com.mercadolibre.calculadorametroscuadrados.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Data
public class ErrorMessageDtoValidations extends ErrorMessage {


    private Map<String, String> fields;

    public ErrorMessageDtoValidations(int value, String validations_error, HashMap<String, String> fields) {
        super(value, validations_error);
        this.setFields(fields);
    }
}
