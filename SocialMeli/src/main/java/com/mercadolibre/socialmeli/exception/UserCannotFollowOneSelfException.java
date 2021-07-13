package com.mercadolibre.socialmeli.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserCannotFollowOneSelfException extends ResponseStatusException {

    public UserCannotFollowOneSelfException(){
        super(HttpStatus.CONFLICT, "User cannot follow oneself");
    }

}
