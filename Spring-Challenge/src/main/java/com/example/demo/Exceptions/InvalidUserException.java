package com.example.demo.Exceptions;

public class InvalidUserException extends ExceptionHandler {

    public InvalidUserException() {
        super();
        this.ERROR = "El ID de usuario ingresado no es válido";
    }

}
