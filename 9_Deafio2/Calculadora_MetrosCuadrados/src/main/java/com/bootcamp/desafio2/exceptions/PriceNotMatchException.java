package com.bootcamp.desafio2.exceptions;
import lombok.Getter;

@Getter
public class PriceNotMatchException extends Exception {

    private String message;

    public PriceNotMatchException(String message) { this.message = message;}

}
