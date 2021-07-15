package com.mercadolibre.desafiospring.exception.user;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class InvalidUserException extends SocialMeliException {
    public InvalidUserException(String msg) {
        super(msg);
    }
}
