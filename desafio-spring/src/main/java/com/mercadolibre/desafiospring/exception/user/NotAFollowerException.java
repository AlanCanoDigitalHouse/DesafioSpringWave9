package com.mercadolibre.desafiospring.exception.user;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class NotAFollowerException extends SocialMeliException {
    public NotAFollowerException() {
        super("The specified seller is not followed by the user.");
    }
}
