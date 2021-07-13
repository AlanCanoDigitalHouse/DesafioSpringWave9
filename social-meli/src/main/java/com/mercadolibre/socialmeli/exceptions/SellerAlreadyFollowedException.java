package com.mercadolibre.socialmeli.exceptions;

public class SellerAlreadyFollowedException extends Exception {

    public SellerAlreadyFollowedException(Integer sellerId) {
        super("Seller already followed: " + sellerId);
    }

}
