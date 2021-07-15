package com.example.demo.Exceptions;

public class InvalidUserCustomException extends CustomExceptionHandler {

    public InvalidUserCustomException() {
        super();
        this.ERROR = "El ID de usuario ingresado no es válido";
    }

}
