package com.desafio2.spring.tucasita.tucasita.exceptions;

public class DistrictDoesNotExistException extends ServiceExceptions{

    public DistrictDoesNotExistException(String disctrictName){
        super();
        this.ERROR = "No existe barrio con nombre " + disctrictName;
    }
}
