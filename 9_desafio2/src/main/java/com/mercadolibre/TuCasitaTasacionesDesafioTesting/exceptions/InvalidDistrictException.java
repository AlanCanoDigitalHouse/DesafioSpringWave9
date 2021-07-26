package com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class InvalidDistrictException extends RuntimeException{

    public InvalidDistrictException(String message) {
        super(message);
    }
}
