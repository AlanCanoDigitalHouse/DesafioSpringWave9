package com.mercadolibre.desafiospring.exception.user;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class SellerDoesNotExistsException extends SocialMeliException {
    public SellerDoesNotExistsException() {
        super("A seller with that id does not exist.");
    }
}
