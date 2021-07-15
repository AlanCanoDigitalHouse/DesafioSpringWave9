package com.mercadolibre.desafiospring.exception.user;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class UserDoesNotExistsException extends SocialMeliException {
    public UserDoesNotExistsException() {
        super("No user with that id exists.");
    }
}
