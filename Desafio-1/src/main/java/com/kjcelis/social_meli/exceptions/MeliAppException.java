package com.kjcelis.social_meli.exceptions;

public class MeliAppException extends Exception {

    private Integer status;

    public MeliAppException(String message, Integer status) {
        super(message);
        this.status = status;
    }
}
