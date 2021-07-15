package com.example.demo.Exceptions;

public class NoRelationCustomException extends CustomExceptionHandler {

    public NoRelationCustomException() {
        super();
        this.ERROR = "No existe la relación entre los dos usuarios ingresados";
    }

}
