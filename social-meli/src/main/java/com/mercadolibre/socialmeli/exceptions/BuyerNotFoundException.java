package com.mercadolibre.socialmeli.exceptions;

public class BuyerNotFoundException extends Exception {

    public BuyerNotFoundException(Integer id) {
        super("buyer not found: " + id);
    }

}
