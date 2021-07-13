package com.mercadolibre.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SellerAlreadyFollowedException extends ResponseStatusException {

    public SellerAlreadyFollowedException(Integer sellerId) {
        super(HttpStatus.BAD_REQUEST, "Seller already followed: " + sellerId);
    }

}
