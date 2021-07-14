package com.desafiospring.socialmeli.exceptions;

public class NotFollowingException extends UserException {

    public NotFollowingException(){
        super();
        this.ERROR = "El usuario no sigue al vendedor";
    }
}
