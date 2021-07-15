package com.mercadolibre.desafiospring.exception.post;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class InvalidProductException extends SocialMeliException {
    public InvalidProductException(String msg) {
        super(msg);
    }
}
