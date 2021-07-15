package com.mercadolibre.desafio.demo.exceptions.userException.exceptions;

import lombok.Getter;

@Getter
public class NotFoundUserException extends RuntimeException{
    private final String MESSAGE = "Bad Request";

    public NotFoundUserException(String message) {
        super(message);
    }
}
