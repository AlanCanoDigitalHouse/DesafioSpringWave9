package com.desafio.tucasitatasaciones.exception;

public class DistrictNotFoundException extends Exception{
    public String ERROR;

    public DistrictNotFoundException(String name){
        super();
        this.ERROR = "District " + name + " does not exists.";
    }
}
