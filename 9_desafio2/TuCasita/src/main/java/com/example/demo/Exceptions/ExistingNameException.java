package com.example.demo.Exceptions;

public class ExistingNameException extends CustomExceptionHandler {

    public ExistingNameException(String districtName) {
        this.ERROR = "District name '" + districtName + "'  is already in use";
    }

}
