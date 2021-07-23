package com.squareMeter.exception.exception;

public class DistrictAlreadyExistsException extends Exception{
    public DistrictAlreadyExistsException(String name) {
        super("The district "+name+" already exists");
    }
}
