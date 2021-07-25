package com.meli.tucasita.exception;

import com.meli.tucasita.utils.Constant;
import org.springframework.http.HttpStatus;

public class DataBaseException extends CasaException{
    public DataBaseException() {
        super(Constant.ERROR_CARGA_BD, HttpStatus.BAD_REQUEST);
    }
}
