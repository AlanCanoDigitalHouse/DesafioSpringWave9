package com.mercadolibre.desafio.spring.exceptions;

public class ErrorUserException extends RuntimeException{

    public ErrorUserException(String message){
        super(message);
    }
}
