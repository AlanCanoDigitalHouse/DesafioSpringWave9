package com.meli.itbootcamp.exceptions;

public class PostException extends Exception {

    public static final String ERROR = "Can't add new Post to Seller";

    public PostException(String msg) {
        super(msg);
    }


}
