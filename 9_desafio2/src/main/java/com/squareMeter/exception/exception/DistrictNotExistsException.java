package com.squareMeter.exception.exception;

public class DistrictNotExistsException extends Exception{
    public DistrictNotExistsException(String name) {
        super("The district "+name+" don't exists");
    }
}
