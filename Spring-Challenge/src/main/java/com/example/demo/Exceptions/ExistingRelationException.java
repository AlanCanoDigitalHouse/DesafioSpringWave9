package com.example.demo.Exceptions;

public class ExistingRelationException extends ExceptionHandler {

    public ExistingRelationException(){
        super();
        this.ERROR = "La relación entre ambos usuarios ya existe";
    }

}
