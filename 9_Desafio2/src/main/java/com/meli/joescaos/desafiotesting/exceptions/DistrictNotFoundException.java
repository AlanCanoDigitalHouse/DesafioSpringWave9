package com.meli.joescaos.desafiotesting.exceptions;

public class DistrictNotFoundException extends RuntimeException {
    public final String ERROR = "District not found in data base";

    public DistrictNotFoundException() {
        super();
    }
}
