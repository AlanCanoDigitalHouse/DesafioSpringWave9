package com.mercadolibre.desafiospring.exception.user;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class AlreadyFollowingException extends SocialMeliException {
    public AlreadyFollowingException() {
        super("User is already following that seller.");
    }
}
