package com.example.demo.exceptions;

import lombok.Data;

@Data
public class DistrictNotFound extends Exception{

    public final String ERROR = "El nombre del distrito no se encuentra dentro de los distritos válidos";

    public DistrictNotFound() {
        super();
    }
}
