package com.mercadolibre.desafiospring.exception;

public class InvalidSortCriteriaException extends SocialMeliException {
    public InvalidSortCriteriaException() {
        super("Invalid sorting order name.");
    }
}
