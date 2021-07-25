package com.meli.tucasita.exception;

import com.meli.tucasita.utils.Constant;
import org.springframework.http.HttpStatus;

public class NoDistrictFoundException extends CasaException{
    public NoDistrictFoundException(String distrito) {
        super(Constant.ERROR_NO_DISTRITO.concat(distrito), HttpStatus.BAD_REQUEST);
    }
}
