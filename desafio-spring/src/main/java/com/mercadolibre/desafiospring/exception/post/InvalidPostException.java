package com.mercadolibre.desafiospring.exception.post;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class InvalidPostException extends SocialMeliException {
    public InvalidPostException(String msg) {
        super(msg);
    }
}
