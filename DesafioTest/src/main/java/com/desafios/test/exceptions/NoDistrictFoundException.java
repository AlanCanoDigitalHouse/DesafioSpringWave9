package com.desafios.test.exceptions;

public class NoDistrictFoundException extends RuntimeException {

    public String getMessage() {
        return "El distrito no existe";
    }
}
