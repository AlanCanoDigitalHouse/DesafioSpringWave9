package com.mercadolibre.socialmeli.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class SellerNotFollowedException extends ResponseStatusException {

    public SellerNotFollowedException(Integer sellerId) {
        super(HttpStatus.BAD_REQUEST, "Seller not followed: " + sellerId);
    }

}
