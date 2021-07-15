package com.mercadolibre.socialmeli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyUnFollowSellerException extends ResponseStatusException {
    public UserAlreadyUnFollowSellerException(){
        super(HttpStatus.CONFLICT, "User already unfollow seller");
    }

}
