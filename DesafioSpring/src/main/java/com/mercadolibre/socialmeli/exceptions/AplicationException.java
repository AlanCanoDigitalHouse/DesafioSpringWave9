package com.mercadolibre.socialmeli.exceptions;

import lombok.Getter;

@Getter
public class AplicationException extends RuntimeException {

    private String data;

    public AplicationException(String msj, String data) {
        super(msj);
        this.data = data;
    }
}
