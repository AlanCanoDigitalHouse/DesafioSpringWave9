package com.meli.socialmeli.exceptions;

import com.meli.socialmeli.utils.Constant;
import org.springframework.http.HttpStatus;

public class DataBaseException extends SocialMeliExceptions {
    public DataBaseException() {
        super(Constant.ERROR_CARGA_BD, HttpStatus.BAD_REQUEST);
    }
}
