package com.mercadolibre.squaremeter.exceptions;

import lombok.Data;

@Data
public class DistrictNotExist extends Exception {

    String Error = "The District not exist";

    public DistrictNotExist() {

        super();
    }
}
