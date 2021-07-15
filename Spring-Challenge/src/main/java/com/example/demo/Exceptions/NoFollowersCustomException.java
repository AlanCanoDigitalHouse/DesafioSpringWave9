package com.example.demo.Exceptions;

public class NoFollowersCustomException extends CustomExceptionHandler {

    public NoFollowersCustomException() {
        super();
        this.ERROR = "El usuario no tiene seguidores";
    }

}
