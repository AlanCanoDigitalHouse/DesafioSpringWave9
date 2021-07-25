package com.meli.socialmeli.exceptions;

import org.springframework.http.HttpStatus;

public class DateNotValidException extends SocialMeliExceptions {
    public DateNotValidException(String mensaje, HttpStatus httpStatus) {
        super(mensaje, httpStatus);
    }
}
