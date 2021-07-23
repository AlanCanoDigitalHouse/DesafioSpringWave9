package com.example.desafiotesting.exceptions;

public class DistrictException extends Exception {
    public static final String ID_NOT_FOUND = "District ID %d not found in the database";
    public static final String NAME_NOT_FOUND = "District name %s not found in the database";
    public static final String PRICE_NOT_EQUALS = "Input price is not equals to the database price";

    public DistrictException(String message) {
        super(message);
    }
}
