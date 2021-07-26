package com.example.tucasitatasaciones.exceptions;


public class DistrictException extends Exception{
    public String ERROR;

    public DistrictException(String district_name) {
        this.ERROR = "The district " + district_name + " is not in the database.";
    }
}
