package com.mercadolibre.desafiospring.exception.post;

import com.mercadolibre.desafiospring.exception.SocialMeliException;

public class InvalidCategoryException extends SocialMeliException {
    public InvalidCategoryException() {
        super("Category does not exists.");
    }
}
