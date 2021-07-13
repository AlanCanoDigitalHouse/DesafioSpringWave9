package com.mercadolibre.desafio.exception;


public class PostException extends Exception {
    public static final String ID_NOT_FOUND = "post id not found";;

    public PostException() {
        super();
    }

    public PostException(String msg) {
        super(msg);
    }
}
