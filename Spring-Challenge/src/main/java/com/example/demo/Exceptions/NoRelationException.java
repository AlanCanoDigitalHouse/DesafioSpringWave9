package com.example.demo.Exceptions;

public class NoRelationException extends ExceptionHandler {

    public NoRelationException() {
        super();
        this.ERROR = "No existe la relación entre los dos usuarios ingresados";
    }

}
