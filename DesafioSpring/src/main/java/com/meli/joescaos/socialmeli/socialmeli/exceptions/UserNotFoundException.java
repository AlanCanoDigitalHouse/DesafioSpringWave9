package com.meli.joescaos.socialmeli.socialmeli.exceptions;



public class UserNotFoundException extends RuntimeException{
    public final String ERROR = "Status Code 400 (Bad Request)";

    public UserNotFoundException() {
        super();
    }
}
