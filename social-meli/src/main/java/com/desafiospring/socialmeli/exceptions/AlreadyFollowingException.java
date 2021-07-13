package com.desafiospring.socialmeli.exceptions;

public class AlreadyFollowingException extends UserException {

    public AlreadyFollowingException() {
        super();
        this.ERROR = "El usuario ya sigue al vendedor";
    }
}
