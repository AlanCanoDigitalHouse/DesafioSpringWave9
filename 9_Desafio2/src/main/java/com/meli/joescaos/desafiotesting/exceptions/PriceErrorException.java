package com.meli.joescaos.desafiotesting.exceptions;

public class PriceErrorException extends  RuntimeException {
    public static final String ERROR = "Price provided is wrong";

    public PriceErrorException() {
        super();
    }
}
