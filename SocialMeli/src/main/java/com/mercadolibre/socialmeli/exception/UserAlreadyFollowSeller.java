package com.mercadolibre.socialmeli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyFollowSeller extends ResponseStatusException {
    public UserAlreadyFollowSeller(){
        super(HttpStatus.CONFLICT, "User already follow seller");
    }
}
