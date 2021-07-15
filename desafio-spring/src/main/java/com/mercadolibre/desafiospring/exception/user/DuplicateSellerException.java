package com.mercadolibre.desafiospring.exception.user;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class DuplicateSellerException extends SocialMeliException {
    public DuplicateSellerException() {
        super("Seller with that id already exists");
    }
}
