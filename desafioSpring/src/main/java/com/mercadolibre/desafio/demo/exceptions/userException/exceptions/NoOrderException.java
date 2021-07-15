package com.mercadolibre.desafio.demo.exceptions.userException.exceptions;

import java.util.List;


public class NoOrderException extends RuntimeException{
    private String MESSAGE;

    private final List<String> orders;

    public NoOrderException(String message, List<String> orders) {
        super(message);
        this.orders = orders;
        this.MESSAGE = message;
    }

    public String getMessage() {
//        String response = "";
//        orders.forEach(m -> MESSAGE += "'"+m+"' ");
        return MESSAGE + orders;
    }
}
