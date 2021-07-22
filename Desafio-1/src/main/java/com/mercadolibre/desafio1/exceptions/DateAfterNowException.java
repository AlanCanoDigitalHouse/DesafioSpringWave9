package com.mercadolibre.desafio1.exceptions;

public class DateAfterNowException extends Exception{
    public DateAfterNowException(String message) {
        super(message);
    }
}
