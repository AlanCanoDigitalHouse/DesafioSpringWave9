package com.example.tucasitatasaciones.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DistrictException extends Exception{
    public String ERROR = "";

    public DistrictException(String district_name) {
        this.ERROR = "The district " + district_name + " is not in the database.";
    }
}
