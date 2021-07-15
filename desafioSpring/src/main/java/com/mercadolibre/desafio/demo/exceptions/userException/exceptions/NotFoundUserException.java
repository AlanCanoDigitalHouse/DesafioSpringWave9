package com.mercadolibre.desafio.demo.exceptions.userException.exceptions;

import lombok.Getter;

@Getter
public class NotFoundUserException extends RuntimeException{
    private final String MESSAGE = "User not found.";

    public NotFoundUserException(String message) {
        super(message);
    }
}
