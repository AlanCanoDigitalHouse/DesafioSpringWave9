package com.example.demo.Exceptions;

public class NoFollowingCustomException extends CustomExceptionHandler {

    public NoFollowingCustomException() {
        super();
        this.ERROR = "El usuario no sigue a nadie";
    }

}
