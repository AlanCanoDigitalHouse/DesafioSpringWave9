package com.mercadolibre.desafiospring.exception.user;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class DuplicateClientException extends SocialMeliException {
    public DuplicateClientException() {
        super("Client with that id already exists");
    }
}
