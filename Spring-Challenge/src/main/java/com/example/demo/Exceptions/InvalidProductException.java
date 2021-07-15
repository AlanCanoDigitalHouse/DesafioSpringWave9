package com.example.demo.Exceptions;

public class InvalidProductException extends ExceptionHandler {

    public InvalidProductException() {
        super();
        this.ERROR = "El ID de producto ingresado no es válido";
    }

}
