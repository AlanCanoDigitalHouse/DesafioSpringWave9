package com.example.demo.Exceptions;

public class NoFollowersException extends ExceptionHandler {

    public NoFollowersException() {
        super();
        this.ERROR = "El usuario no tiene seguidores";
    }

}
