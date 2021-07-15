package com.example.demo.Exceptions;

public class DuplicatedPostCustomException extends CustomExceptionHandler {

    public DuplicatedPostCustomException() {
        super();
        this.ERROR = "Post duplicado";
    }

}
