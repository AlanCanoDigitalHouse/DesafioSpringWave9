package com.example.demo.Exceptions;

public class InvalidProductCustomException extends CustomExceptionHandler {

    public InvalidProductCustomException() {
        super();
        this.ERROR = "El ID de producto ingresado no es válido";
    }

}
