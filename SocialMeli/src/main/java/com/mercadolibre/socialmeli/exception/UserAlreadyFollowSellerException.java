package com.mercadolibre.socialmeli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyFollowSellerException extends ResponseStatusException {
    public UserAlreadyFollowSellerException(){
        super(HttpStatus.CONFLICT, "User already follow seller");
    }
}
