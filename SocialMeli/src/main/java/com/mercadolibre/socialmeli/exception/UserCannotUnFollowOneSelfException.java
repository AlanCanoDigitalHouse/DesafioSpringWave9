package com.mercadolibre.socialmeli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserCannotUnFollowOneSelfException extends ResponseStatusException {

    public UserCannotUnFollowOneSelfException(){
        super(HttpStatus.CONFLICT, "User cannot unfollow oneself");
    }

}
