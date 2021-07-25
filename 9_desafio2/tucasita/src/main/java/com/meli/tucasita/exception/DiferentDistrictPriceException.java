package com.meli.tucasita.exception;

import com.meli.tucasita.utils.Constant;
import org.springframework.http.HttpStatus;

public class DiferentDistrictPriceException extends CasaException{
    public DiferentDistrictPriceException() {
        super(Constant.ERROR_PRECIO_DISTRITO, HttpStatus.BAD_REQUEST);
    }
}
