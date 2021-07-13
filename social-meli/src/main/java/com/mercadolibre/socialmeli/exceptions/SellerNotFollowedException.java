package com.mercadolibre.socialmeli.exceptions;

public class SellerNotFollowedException extends Exception {

    public SellerNotFollowedException(Integer sellerId) {
        super("Seller not followed: " + sellerId);
    }

}
