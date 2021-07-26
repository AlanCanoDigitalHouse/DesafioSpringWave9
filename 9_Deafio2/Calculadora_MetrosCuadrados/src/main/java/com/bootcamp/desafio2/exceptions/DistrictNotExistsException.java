package com.bootcamp.desafio2.exceptions;

import lombok.Getter;

@Getter
public class DistrictNotExistsException extends Exception {

    private String message;

    public DistrictNotExistsException(String message) { this.message = message;}
}
