package com.mercadolibre.socialmeli.exceptions;

public class SellerNotFoundException extends Exception {

    public SellerNotFoundException(Integer id) {
        super("seller not found: " + id);
    }

}
