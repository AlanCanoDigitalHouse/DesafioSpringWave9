package com.mercadolibre.desafio.demo.exceptions.userException.exceptions;

import lombok.Getter;

@Getter
public class NameException extends RuntimeException {
    private final String name;
    public NameException(String message, String name) {
        super(message);
        this.name = name;
    }


    public String getCustomMessage() {
        return "The 'userName: " + name + "' parameter entered does not match the seller 'userName' attribute. ";
    }
}
