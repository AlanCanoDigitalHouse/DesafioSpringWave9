package com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class NullListException extends RuntimeException{

    public NullListException(String message){
        super(message);
    }
}
