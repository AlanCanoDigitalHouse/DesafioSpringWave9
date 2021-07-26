package com.mercadolibre.TuCasitaTasacionesDesafioTesting.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor

public class EmptyListException extends RuntimeException{

    public EmptyListException(String message){
        super(message);
    }
}
