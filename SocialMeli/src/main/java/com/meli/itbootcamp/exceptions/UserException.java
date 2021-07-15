package com.meli.itbootcamp.exceptions;

public class UserException extends Exception {

    public static final String SAME_USER = "Can't follow oneself";
    public static final String NOT_SELLER = "UserId didn't match any Seller";
    public static final String NOT_USER = "UserId didn't match any User";

    public UserException(String msg) {
        super(msg);
    }
}
