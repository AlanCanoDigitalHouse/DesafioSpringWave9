package com.mercadolibre.desafiospring.exception;

public abstract class SocialMeliException extends Exception {
    public SocialMeliException(String msg) {
        super(msg);
    }

    public SocialMeliException() {
        super();
    }
}
