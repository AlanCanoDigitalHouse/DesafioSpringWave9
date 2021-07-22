package com.mercadolibre.desafiotesting.exceptions;

public class DistrictException extends  Exception{


    public static final String DISTRICT_NOT_FOUND = "district not found";

    public DistrictException() {
        super();
    }

    public DistrictException(String msg) {
        super(msg);
    }

}
