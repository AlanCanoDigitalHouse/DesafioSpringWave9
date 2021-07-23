package com.example.demo.Exceptions;

public class NotFoundException extends CustomExceptionHandler {

    public NotFoundException(String districtName) {
        this.ERROR = "District '" + districtName + "'  does not exist";
    }

}
