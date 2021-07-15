package com.example.demo.Exceptions;

public class DuplicatedPostException extends ExceptionHandler {

    public DuplicatedPostException() {
        super();
        this.ERROR = "Post duplicado";
    }

}
