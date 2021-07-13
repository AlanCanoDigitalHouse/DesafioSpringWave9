package com.mercadolibre.desafio.exception;

public class UserException extends Exception {
    public static final String ID_NOT_FOUND = "user id not found";
    public static final String USER_ALREADY_FOLLOWED = "user already followed";
    public static final String USER_NOT_ALREADY_FOLLOWED = "user already followed";

    public UserException() {
        super();
    }

    public UserException(String msg) {
        super(msg);
    }
}
