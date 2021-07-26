package com.mercadolibre.calculadorametroscuadrados.exception;

import lombok.Getter;

public class DistrictDoesntExistException extends Throwable {

    private final String name;

    public DistrictDoesntExistException(String district) {
        this.name = district;
    }

    public String getCustomMessage(){
        return "District \"" + this.name + "\" does not exist or was not loaded";
    }
}
