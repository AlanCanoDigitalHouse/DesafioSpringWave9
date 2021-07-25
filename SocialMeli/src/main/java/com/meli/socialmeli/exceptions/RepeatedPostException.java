package com.meli.socialmeli.exceptions;

import org.springframework.http.HttpStatus;

public class RepeatedPostException extends SocialMeliExceptions {

    public RepeatedPostException(String mensaje, HttpStatus httpStatus) {
        super(mensaje, httpStatus);
    }
}
