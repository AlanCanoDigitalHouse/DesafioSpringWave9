package com.mercadolibre.desafiotesting.exceptions;

public class HouseException extends Exception {

    public static final String MAX_PRICE = "El precio máximo permitido por metro cuadrado no puede superar los 4000 U$S.";

    public HouseException() {
        super();
    }

    public HouseException(String msg) {
        super(msg);
    }

}
